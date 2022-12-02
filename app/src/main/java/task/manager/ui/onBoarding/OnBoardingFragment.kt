package task.manager.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import me.relex.circleindicator.CircleIndicator
import task.manager.R
import task.manager.data.local.Pref
import task.manager.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter{
            if (FirebaseAuth.getInstance().currentUser?.uid == null){
                findNavController().navigate(R.id.authFragment)
            }else{
                pref.saveShowBoarding(true)
                findNavController().navigateUp()
            }
        }
        binding.viewPager.adapter = adapter

       binding.viewPager.adapter = adapter
        binding.circleIndicator.setViewPager(binding.viewPager.findViewById(R.id.view_pager))



    }

}