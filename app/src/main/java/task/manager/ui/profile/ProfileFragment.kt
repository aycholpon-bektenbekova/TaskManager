package task.manager.ui.profile


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import task.manager.data.local.Pref
import task.manager.databinding.FragmentProfile2Binding


class ProfileFragment : Fragment() {

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2005
    private lateinit var binding: FragmentProfile2Binding
    private lateinit var pref: Pref

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()){
                imageUri: Uri? ->
            //binding.profileImage.setImageURI(imageUri)

            Glide.with(this).load(imageUri.toString()).into(binding.profileImage)
            pref.saveImage(imageUri.toString())
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

        profileAttributes()


    }

    private fun profileAttributes() {
        binding.etName.setText(pref.getName())

        binding.etName.addTextChangedListener {

            pref.saveName(binding.etName.text.toString())
        }
        pref.getAge()?.let { binding.etAge.setText(it) }
        binding.etAge.addTextChangedListener {
            pref.saveAge(binding.etAge.text.toString())
        }

        if (pref.getImage()!= ""){
            Glide.with(this).load(pref.getImage()).into(binding.profileImage)
        }
    }
}


