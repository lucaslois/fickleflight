package com.example.tp2.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tp2.R

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paymentDetailsView = view.findViewById<View>(R.id.optionPaymentDetails)
        paymentDetailsView.findViewById<ImageView>(R.id.optionIcon).setImageResource(R.drawable.ic_payment)
        paymentDetailsView.findViewById<TextView>(R.id.optionText).text = "Payment Details"

        val referralCodeView = view.findViewById<View>(R.id.optionReferralCode)
        referralCodeView.findViewById<ImageView>(R.id.optionIcon).setImageResource(R.drawable.ic_referral)
        referralCodeView.findViewById<TextView>(R.id.optionText).text = "Referral Code"
        referralCodeView.findViewById<TextView>(R.id.optionBadge).visibility = View.VISIBLE

        val settingsView = view.findViewById<View>(R.id.optionSettings)
        settingsView.findViewById<ImageView>(R.id.optionIcon).setImageResource(R.drawable.ic_settings)
        settingsView.findViewById<TextView>(R.id.optionText).text = "Settings"

        val logoutView = view.findViewById<View>(R.id.optionLogout)
        logoutView.findViewById<ImageView>(R.id.optionIcon).setImageResource(R.drawable.ic_logout)
        logoutView.findViewById<TextView>(R.id.optionText).text = "Logout"
    }
}