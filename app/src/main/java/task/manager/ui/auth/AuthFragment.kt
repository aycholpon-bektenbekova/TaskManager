package task.manager.ui.auth


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import task.manager.databinding.FragmentAuthBinding
import java.util.concurrent.TimeUnit
import task.manager.R


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private lateinit var uid: String
    private lateinit var callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken


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
            initCallback()
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(binding.inAuth.etPhoneNumber.text.toString())
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
        binding.inAccept.btnCode.setOnClickListener {
            examCode()
        }
    }

    private fun examCode() {
        val credential = PhoneAuthProvider.getCredential(uid,
            binding.inAccept.etCode.text.toString()
        )

        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful){
                    Log.d("aic", "signInWithCredential:success")
                    findNavController().navigate(R.id.navigation_home)
                }else Log.d("aic", "signInWithCredential:error")
            }
    }

    private fun initCallback() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
//                Log.d(TAG, "onVerificationCompleted:$credential")
//                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
//                Log.w(TAG, "onVerificationFailed", e)
                Log.d("ololo", e.message.toString())
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
//                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                uid = verificationId
                resendToken = token
                Log.d("aic", "auth succd")

                binding.acceptContainer.isVisible = true
                binding.authContainer.isVisible = false

            }
        }

    }


}

