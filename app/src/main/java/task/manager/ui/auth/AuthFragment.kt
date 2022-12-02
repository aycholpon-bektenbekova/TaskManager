package task.manager.ui.auth


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import task.manager.databinding.FragmentAuthBinding
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.inAuth.btnSms.setOnClickListener {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(binding.inAuth.etPhoneNumber.text.toString())
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
        binding.inAccept.btnSms.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(
                verificationId!!,
                binding.inAccept.etPhoneNumber.text.toString()
            )

        }
    }
     fun onVerificationCompleted(credential: PhoneAuthCredential) {
        signInWithPhoneAuthCredential(credential)
    }
     fun onVerificationFailed(e: FirebaseException) {

        if (e is FirebaseAuthInvalidCredentialsException) {

        } else if (e is FirebaseTooManyRequestsException) {

        }
    }
}

private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithCredential(credential)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                val uid = auth.currentUser?.uid
            } else {

                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                }
                // Update UI
            }
        }
}

