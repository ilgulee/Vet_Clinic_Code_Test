package ilgulee.com.asurionvetcliniccodetest.screens.contact


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ilgulee.com.asurionvetcliniccodetest.R
import ilgulee.com.asurionvetcliniccodetest.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(
            inflater,
            R.layout.fragment_contact,
            container,
            false
        )
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this).get(
            ContactViewModel::class.java)
        binding.viewModel = viewModel

        val adapter =   PetAdapter(PetListener {
                url -> viewModel.onPetClicked(url)
        })
        binding.recyclerView.adapter = adapter

        viewModel.petList.observe(this.viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        viewModel.navigateToPetDetail.observe(viewLifecycleOwner, Observer { url ->
            url?.let{
                this.findNavController().
                    navigate(ContactFragmentDirections.actionContactFragmentToPetDetailFragment()
                        .setContentUrl(url))
                viewModel.onPetDetailedNavigated()
            }
        })

        viewModel.config.observe(viewLifecycleOwner, Observer { config->
            config?.let{
                if(config.isCallEnabled){
                    binding.callButton.visibility=View.VISIBLE
                }
                if(config.isChatEnabled){
                    binding.chatButton.visibility=View.VISIBLE
                }
                binding.businessHour.text = config.workHours
            }
        })

        viewModel.isBusinessDayAndTime.observe(viewLifecycleOwner, Observer {
            it?.let{
                if(it){
                    val dialogBuilder = AlertDialog.Builder(activity!!)
                    dialogBuilder.setMessage(R.string.within_work_hour)
                        .setCancelable(true)
                        .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener {
                                dialog, id ->
                            dialog.dismiss()
                        })
                    val alert = dialogBuilder.create()
                    alert.show()
                    viewModel.confirmclicked()
                }else{
                    val dialogBuilder = AlertDialog.Builder(activity!!)
                    dialogBuilder.setMessage(R.string.outside_work_hour)
                        .setCancelable(true)
                        .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener {
                                dialog, id ->
                            dialog.dismiss()

                        })
                    val alert = dialogBuilder.create()
                    alert.show()
                    viewModel.confirmclicked()
                }
            }
        })
        return binding.root
    }


}
