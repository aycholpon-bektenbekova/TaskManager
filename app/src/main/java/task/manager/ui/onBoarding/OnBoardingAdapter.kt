package task.manager.ui.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import task.manager.R
import task.manager.data.model.OnBoard
import task.manager.databinding.ItemOnBoardingBinding

class OnBoardingAdapter(private val onStartClick: () -> Unit):
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

    private val arrayList = arrayListOf(
        OnBoard(R.raw.lottie1.toString(), "Notepad", "1"),
        OnBoard(R.raw.lottie2.toString(), "Sketchbook", "2"),
        OnBoard(R.raw.lottie3.toString(), "Sticky Note", "3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnBoardingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int = arrayList.size


    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.skip.setOnClickListener {
                onStartClick()
            }
            binding.btnStart.setOnClickListener {
                onStartClick()
            }
         binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.btnStart.isVisible = adapterPosition == arrayList.lastIndex
            binding.skip.isVisible = adapterPosition != arrayList.lastIndex
        }
    }
}