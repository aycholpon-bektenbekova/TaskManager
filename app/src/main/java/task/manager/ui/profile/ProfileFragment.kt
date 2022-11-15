package task.manager.ui.profile

import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import task.manager.R
import task.manager.databinding.FragmentProfile2Binding


class ProfileFragment : Fragment() {

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2005
    private lateinit var binding: FragmentProfile2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentProfile2Binding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent: Intent
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            startActivityForResult(this, IMAGE_GALLERY_REQUEST_CODE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_GALLERY_REQUEST_CODE){
            if (data!= null && data.data != null){
                val image = data.data
                val source = ImageDecoder.createSource(requireActivity().contentResolver, image!!)
                val bitmap = ImageDecoder.decodeBitmap(source)
                binding.profileImage.setImageBitmap(bitmap)
            }
        }
    }
}