import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.tictactoeapp.MatchHistoryAdapter
import com.rafael.tictactoeapp.MatchItemListener
import com.rafael.tictactoeapp.databinding.MatchHistoryFragmentBinding
import com.rafael.tictactoeapp.model.Match
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel

class MatchHistoryFragment : MatchItemListener, Fragment() {

    private var _binding: MatchHistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mTicTacViewModel: TicTacViewModel
    private lateinit var adapter: MatchHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchHistoryFragmentBinding.inflate(inflater, container, false)


        //Recyclerview
        adapter = MatchHistoryAdapter(matchItemListener = this )
        var recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //viewmodel and observer
        mTicTacViewModel = ViewModelProvider(this).get(TicTacViewModel::class.java)
        mTicTacViewModel.readAllData.observe(
            viewLifecycleOwner,
            Observer { match: MutableList<Match> -> adapter.setData(match) }
        )
        mTicTacViewModel.deleteResult.observe(viewLifecycleOwner, {rowCount ->
            if(rowCount>0){
                updateData()
            }else{
                Toast.makeText(context,"Falhou delete no banco :(",Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    override fun onDeleteItem(id: Int) {
        mTicTacViewModel.deleteMatch(id)
    }

    override fun updateData() {
        if(::adapter.isInitialized){
            mTicTacViewModel.requestData()
        }
    }

}
