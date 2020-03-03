package vladiachuk.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout.*
import vladiachuk.com.bottomsheet.BottomSheetController
import vladiachuk.com.bottomsheet.State

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheet.controller = BottomSheetController(bottomSheet)

        var cusState: State

        bottomSheet.controller!!.run {
            bottomSheet.post {
                cusState = createState(cus)

                possibleStates = arrayListOf(COLLAPSED_STATE, HALF_EXPANDED_STATE, cusState)
                statesGraph = arrayListOf(
                    intArrayOf(COLLAPSED_STATE.id, HALF_EXPANDED_STATE.id),
                    intArrayOf(HALF_EXPANDED_STATE.id, COLLAPSED_STATE.id),
                    intArrayOf(cusState.id, HALF_EXPANDED_STATE.id)
                )
                state = cusState
            }


            btn.setOnClickListener {
                setStateAnim(nextState)
            }

            scbtn.setOnClickListener {
                state = prevState
            }
        }
    }
}
