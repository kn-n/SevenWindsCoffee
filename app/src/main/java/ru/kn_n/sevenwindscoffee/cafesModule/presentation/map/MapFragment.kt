package ru.kn_n.sevenwindscoffee.cafesModule.presentation.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.cafesModule.data.Cafes
import ru.kn_n.sevenwindscoffee.databinding.FragmentMapBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment

class MapFragment: BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate) {

    private lateinit var mapView : MapView

    private val cafes = mutableListOf<CafeResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arguments?.getSerializable("POINTS") as Cafes
        cafes.addAll(list.cafes)

        mapView = binding.mapview

        val imageProvider = ImageProvider.fromResource(requireContext(), R.drawable.mark)
        val textStyle = TextStyle().apply {
            color = ContextCompat.getColor(requireContext(), R.color.brown_1)
            size = 12F
            placement = TextStyle.Placement.BOTTOM
        }

        cafes.forEach{
            mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                geometry = Point(it.point.latitude.toDouble(), it.point.longitude.toDouble())
                setIcon(imageProvider)
                setText(it.name, textStyle)
            }
        }

        mapView.mapWindow.map.move(
            CameraPosition(
                Point(cafes.first().point.latitude.toDouble(), cafes.first().point.longitude.toDouble()),
                9.0f,
                150.0f,
                10.0f
            )
        )
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
        super.onStop()
    }
}