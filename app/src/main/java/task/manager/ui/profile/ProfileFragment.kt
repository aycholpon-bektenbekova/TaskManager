package task.manager.ui.profile

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import task.manager.R
import task.manager.data.local.Pref
import task.manager.databinding.FragmentProfile2Binding


class ProfileFragment : Fragment() {

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2005
    private lateinit var binding: FragmentProfile2Binding
    private lateinit var pref: Pref

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()){
                imageUri: Uri? -> binding.profileImage.setImageURI(imageUri)
        }

    companion object{
        private const val CONTENT_TYPE = "image/*"
    }

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
            getContent.launch(CONTENT_TYPE)
        }
        pref = Pref(requireContext())
        binding.etName.setText(pref.getName())

        binding.etName.addTextChangedListener {

            pref.saveName(binding.etName.text.toString())
        }
    }

}