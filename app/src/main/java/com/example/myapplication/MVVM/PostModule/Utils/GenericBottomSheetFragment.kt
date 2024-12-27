package raw.Utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenericBottomSheetFragment : BottomSheetDialogFragment() {

    private var contentViewProvider : ((LayoutInflater, ViewGroup?) -> View )? = null

    fun setContentView(provider : (LayoutInflater, ViewGroup?) -> View) : GenericBottomSheetFragment {
        contentViewProvider = provider
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return contentViewProvider?.invoke(inflater, container)
            ?: FrameLayout(requireContext()) // An empty View to avoid crashes
    }


    fun showGenericBottomSheet(
        fragmentManager: FragmentManager,
        tag: String,
        contentView: (LayoutInflater, ViewGroup?) -> View
    ) {
        val bottomSheet = GenericBottomSheetFragment().apply {
            setContentView(contentView)
        }
        bottomSheet.show(fragmentManager, tag)
    }
}