package ilgulee.com.asurionvetcliniccodetest.screens.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ilgulee.com.asurionvetcliniccodetest.databinding.ListItemBinding
import ilgulee.com.asurionvetcliniccodetest.model.Pet

class PetAdapter(private val clickListener: PetListener) :
    ListAdapter<Pet, PetAdapter.ViewHolder>(PetDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = getItem(position)
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            pet: Pet,
            clickListener: PetListener
        ) {
            binding.pet = pet
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }

        }
    }
}

class PetDiffCallback : DiffUtil.ItemCallback<Pet>() {
    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return (oldItem.contentUrl == newItem.contentUrl) && (oldItem.dateAdded == newItem.dateAdded)
    }

    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
        return oldItem == newItem
    }
}

class PetListener(val clickListener: (url: String) -> Unit) {
    fun onClick(pet: Pet) = clickListener(pet.contentUrl)
}