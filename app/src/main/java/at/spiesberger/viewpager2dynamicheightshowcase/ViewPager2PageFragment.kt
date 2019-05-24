package at.spiesberger.viewpager2dynamicheightshowcase

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import at.spiesberger.viewpager2dynamicheightshowcase.databinding.ViewPager2PageFragmentBinding
import kotlin.random.Random

const val ARGS_NUMBER_OF_ITEMS = "number_of_items"

class ViewPager2PageFragment : Fragment() {

    private lateinit var viewModel: ViewPager2PageViewModel
    private lateinit var binding: ViewPager2PageFragmentBinding

    companion object {

        @JvmStatic
        fun newInstance(numberOfItems: Int) = ViewPager2PageFragment().apply {
            arguments = Bundle().apply {
                putInt(ARGS_NUMBER_OF_ITEMS, numberOfItems)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(ViewPager2PageViewModel::class.java)
        arguments?.getInt(ARGS_NUMBER_OF_ITEMS)?.let {
            viewModel.numberOfItems = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewPager2PageFragmentBinding.inflate(inflater, container, false)

        binding.clickListener = this
        setVisibility()

        if (viewModel.numberOfItems == 0) {
            binding.root.postDelayed({
                randomVisibility()
            }, 2000)
        }

        return binding.root
    }

    private fun setVisibility() {
        when (viewModel.numberOfItems) {
            0 -> {
                binding.element1.visibility = View.VISIBLE
                binding.element2.visibility = View.VISIBLE
                binding.element3.visibility = View.VISIBLE
                binding.element4.visibility = View.VISIBLE
            }
            1 -> {
                binding.element1.visibility = View.VISIBLE
                binding.element2.visibility = View.GONE
                binding.element3.visibility = View.GONE
                binding.element4.visibility = View.GONE
            }
            2 -> {
                binding.element1.visibility = View.VISIBLE
                binding.element2.visibility = View.VISIBLE
                binding.element3.visibility = View.GONE
                binding.element4.visibility = View.GONE
            }
            3 -> {
                binding.element1.visibility = View.VISIBLE
                binding.element2.visibility = View.VISIBLE
                binding.element3.visibility = View.VISIBLE
                binding.element4.visibility = View.GONE
            }
            4 -> {
                binding.element1.visibility = View.VISIBLE
                binding.element2.visibility = View.VISIBLE
                binding.element3.visibility = View.VISIBLE
                binding.element4.visibility = View.VISIBLE
            }
        }
    }

    fun randomVisibility() {
        binding.element1.visibility = if (Random.nextBoolean()) View.VISIBLE else View.GONE
        binding.element2.visibility = if (Random.nextBoolean()) View.VISIBLE else View.GONE
        binding.element3.visibility = if (Random.nextBoolean()) View.VISIBLE else View.GONE
        binding.element4.visibility = if (Random.nextBoolean()) View.VISIBLE else View.GONE
    }

    fun click(text: String) {
        context?.let {
            val element1 = if (binding.element1.visibility == View.VISIBLE) "VISIBLE" else "GONE"
            val element2 = if (binding.element2.visibility == View.VISIBLE) "VISIBLE" else "GONE"
            val element3 = if (binding.element3.visibility == View.VISIBLE) "VISIBLE" else "GONE"
            val element4 = if (binding.element4.visibility == View.VISIBLE) "VISIBLE" else "GONE"

            Toast.makeText(it, "Clicked on $text:\n\nElement 1: $element1\nElement 2: $element2\nElement 3: $element3\nElement 4: $element4", Toast.LENGTH_LONG).show()
        }
    }
}
