package ilgulee.com.asurionvetcliniccodetest.screens.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ilgulee.com.asurionvetcliniccodetest.R
import ilgulee.com.asurionvetcliniccodetest.databinding.FragmentPetdetailBinding

class PetDetailFragment : Fragment() {
    private lateinit var viewModel: PetDetailViewModel
    private lateinit var viewModelFactory: PetDetailViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPetdetailBinding>(
            inflater,
            R.layout.fragment_petdetail,
            container,
            false
        )
        binding.lifecycleOwner = this
        viewModelFactory =
            PetDetailViewModelFactory(PetDetailFragmentArgs.fromBundle(arguments!!).contentUrl)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PetDetailViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.contentUrl.observe(viewLifecycleOwner, Observer { url ->
            url?.let {
                Toast.makeText(context, url, Toast.LENGTH_LONG).show()
                binding.webview.settings.javaScriptEnabled = true
                binding.webview.settings.loadWithOverviewMode = true
                binding.webview.settings.useWideViewPort = true
                binding.webview.loadUrl(url)
            }
        })
        return binding.root
    }


}
