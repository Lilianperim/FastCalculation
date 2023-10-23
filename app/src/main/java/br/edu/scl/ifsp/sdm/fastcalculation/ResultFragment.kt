package br.edu.scl.ifsp.sdm.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.FragmentResultBinding

class ResultFragment : Fragment(), OnPlayGame {

    private lateinit var resultBinding: FragmentResultBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            settings = it.getParcelable(EXTRA_SETTINGS) ?: Settings()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultBinding = FragmentResultBinding.inflate(inflater, container, false)
        setupClick()
        setupViews()
        return resultBinding.root
    }

    private fun setupClick() = with(resultBinding) {
        restartBt.setOnClickListener { onPlayGame() }
    }

    private fun setupViews() = with(resultBinding) {
        roundTv.text = getString(R.string.points)
        resultTv.text = settings.points
    }

    override fun onPlayGame() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.gameFl, GameFragment.newInstance(settings)).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(settings: Settings): ResultFragment =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_SETTINGS, settings)
                }
            }
    }
}