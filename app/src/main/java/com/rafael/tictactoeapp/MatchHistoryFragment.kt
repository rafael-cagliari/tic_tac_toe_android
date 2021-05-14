import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.tictactoeapp.MatchHistoryAdapter
import com.rafael.tictactoeapp.databinding.MatchHistoryFragmentBinding
import com.rafael.tictactoeapp.viewmodel.TicTacViewModel

class MatchHistoryFragment : Fragment() {

    private var _binding: MatchHistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mTicTacViewModel: TicTacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchHistoryFragmentBinding.inflate(inflater, container, false)


        //Recyclerview
        var adapter = MatchHistoryAdapter()
        var recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //viewmodel and observer
        mTicTacViewModel = ViewModelProvider(this).get(TicTacViewModel::class.java)
        mTicTacViewModel.readAllData.observe(viewLifecycleOwner, Observer { match ->
            adapter.setData(match)
        })

        return binding.root
    }

    }
