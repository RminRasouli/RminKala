package ir.project.rminkala.ui.Review.AddReview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentAddReviewBinding
import ir.project.rminkala.ui.Detail.DetailFragmentArgs
import ir.project.rminkala.ui.Home.ProductViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddReviewFragment : Fragment(R.layout.fragment_add_review) {
    private val args: AddReviewFragmentArgs by navArgs()
    private val addReviewViewModel: AddReviewViewModel by viewModels()
    private var star = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAddReviewBinding.bind(view)

        binding.apply {
            jSlideStar.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {

                }

                override fun onStopTrackingTouch(slider: Slider) {
                    star = slider.value.toInt()
                }

            })

            jSlideStar.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
                when (slider.value.toString()) {
                    "1.0" -> textNumberStar.text = "بد"
                    "2.0" -> textNumberStar.text = "متوسط"
                    "3.0" -> textNumberStar.text = "خوب"
                    "4.0" -> textNumberStar.text = "خیلی خوب"
                    "5.0" -> textNumberStar.text = "عالی"
                }
                star = slider.value.toInt()
            })


            CommentSubmit.setOnClickListener {
                if (star != 0) {
                    if (descriptionEdittext.toString() != "" ) {
                        if (binding.checkBox.isChecked){
                            lifecycleScope.launch {
                                addReviewViewModel.addReview(
                                    args.idProduct,
                                    "کاربر ناشناس",
                                    "ALAKI@gmail.com",
                                    star,
                                    descriptionEdittext.text.toString()
                                )
                            }
                        }else{
                            lifecycleScope.launch {
                                addReviewViewModel.addReview(
                                    args.idProduct,
                                    nameTextView.text.toString(),
                                    "ALAKI@gmail.com",
                                    star,
                                    descriptionEdittext.text.toString()
                                )
                            }
                        }
                        Toast.makeText(
                            requireContext(),
                            "نظر شما به ثبت رسید",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "لطفا قسمت توضیحات را کامل کنید.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "لطفا قسمت امتیاز را کامل کنید.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
