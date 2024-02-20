package yampi.msh.abohava.ui.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import yampi.msh.abohava.databinding.FragmentCitiesBinding
import yampi.msh.abohava.ui.CitiesView

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cities = mutableListOf<String>()
    private var selectedCity = "Gorgan"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val citiesViewModel =
            ViewModelProvider(this).get(CitiesViewModel::class.java)

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val citiesView: ComposeView = binding.citiesView


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                citiesViewModel.uiViewState.collect { uiState ->
                    when (uiState) {
                        is CitiesViewState.City -> {
                            selectedCity = uiState.name.orEmpty()
                            Log.d("CitiesFragment", "Selected city: ${uiState.name}")
                        }

                        is CitiesViewState.Cities -> {
                            Log.d("CitiesFragment", "Cities: ${uiState.cities}")
                            if (uiState.cities.isEmpty()) {
                                cities.clear()
                            } else {
                                cities.clear()
                                cities.addAll(uiState.cities)
                            }
                        }
                    }
                }
            }
        }

        citiesViewModel.getCities()
        citiesViewModel.getSelectedCity()

        citiesView.apply {
            setContent {
                CitiesView(
                    citiesViewModel = citiesViewModel,
                    selectedCity = selectedCity,
                    onSelectCity = { city -> citiesViewModel.selectCity(city = city) },
                    addCity = { city -> citiesViewModel.addCityAndRefresh(city = city) })
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}