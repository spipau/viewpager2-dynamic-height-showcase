package at.spiesberger.viewpager2dynamicheightshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainActivityFragmentStateAdapter(this)
        viewpager2.adapter = adapter
        adapter.submitList(
            listOf(
                ViewPager2PageFragment.newInstance(3),
                ViewPager2PageFragment.newInstance(2),
                ViewPager2PageFragment.newInstance(4),
                ViewPager2PageFragment.newInstance(1)
            )
        )

        val randomAdapter = MainActivityFragmentStateAdapter(this)
        viewpager2_delay.adapter = randomAdapter
        randomAdapter.submitList(
            listOf(
                ViewPager2PageFragment.newInstance(0),
                ViewPager2PageFragment.newInstance(0),
                ViewPager2PageFragment.newInstance(0),
                ViewPager2PageFragment.newInstance(0)
            )
        )

        reload_random.setOnClickListener {
            (randomAdapter.getItem(viewpager2_delay.currentItem) as? ViewPager2PageFragment)?.randomVisibility()
        }
    }
}

class MainActivityFragmentStateAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val fragmentList = mutableListOf<ViewPager2PageFragment>()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getItemCount(): Int = fragmentList.size

    fun submitList(newList: List<ViewPager2PageFragment>) {
        fragmentList.clear()
        fragmentList.addAll(newList)
    }
}