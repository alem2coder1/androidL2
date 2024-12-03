package kz.test.lab2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.test.lab2.databinding.ItemPersonBinding
import kz.test.lab2.model.entity.Person
import kz.test.lab2.util.PersonDiffUtilCallback

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {
    private val items = arrayListOf<Person>()

    fun setItems(personList: List<Person>) {
        val diffCallback = PersonDiffUtilCallback(items, personList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(personList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(
        private val binding: ItemPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            val info = person.info

            with(binding) {
                name.text = person.name
                title.text = person.title

                if (info == null) return

                birthDeath.text = buildString {
                    append("Birth and Death: ")
                    append(info.born ?: "Unknown")
                    append(" -- ")
                    append(info.died ?: "Unknown")
                }

                dynasty.text = getText(info.dynasty, "Dynasty") ?: "Dynasty not available"

                issue.text = getText(info.issue, "Issue") ?: "No issue"

                occupation.text =
                    getText(info.occupation, "Occupation") ?: "Occupation not available"


                notableWork.text = getText(info.notableWork, "Notable Work") ?: "No notable work"

                restingPlace.text =
                    getText(info.restingPlace, "Resting place") ?: "Resting place not available"

                causeOfDeath.text =
                    getText(info.causeOfDeath, "Cause of death") ?: "Cause of death not available"

                conflicts.text = getText(info.conflicts, "Conflicts") ?: "No conflicts"

                awards.text = getText(info.awards, "Awards") ?: "Awards not available"
            }
        }

    }

    private fun getText(obj: Any?, prefix: String): String? {
        if (obj is String) {
            return "$prefix: $obj"
        } else if (obj is List<*>) {
            return obj.joinToString(", ", "$prefix: ")
        }
        return null
    }
}