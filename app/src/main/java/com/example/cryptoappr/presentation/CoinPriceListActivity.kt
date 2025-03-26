package com.example.cryptoappr.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoappr.R
import com.example.cryptoappr.databinding.ActivityCoinPrceListBinding
import com.example.cryptoappr.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener  = {
                if (isOnePaneMode()) {
                    launchCoinDetailActivity(it.fromSymbol)
                } else {
                    launchCoinDetailFragment(it.fromSymbol)
                }
        }
        val rvCoinPriceList = binding.rvCoinPriceList
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.fragmentContainerCoinDetail == null
    }

    private fun launchCoinDetailFragment(fromSymbol: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_coin_detail,
                CoinDetailFragment.newInstance(fromSymbol)
            )
            .addToBackStack(null).commit()
    }

    private fun launchCoinDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }
}
