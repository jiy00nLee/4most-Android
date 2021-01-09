package org.mydaily.ui.view.signup

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mydaily.R
import org.mydaily.databinding.ActivitySignUpBinding
import org.mydaily.ui.base.BaseActivity
import org.mydaily.ui.viewmodel.SignUpViewModel
import org.mydaily.util.LoginPatternCheckUtil

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()
    var isValidName = false
    var isValidEmail = false
    var isValidPassword = false
    var passwordSame = false
    var passwordIsVisible = true
    var passwordConfirmIsVisible = true

    override fun initView() {
        etColorChange()
        etAlertChange()
        visibleButtonChange()
        navigationClickEvent()
        signUpButtonClickEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun etColorChange() {
        binding.etName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.clName.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.tvName.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etName.text.isEmpty()) {
                    binding.clName.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvName.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clEmail.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.tvEmail.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etEmail.text.isEmpty()) {
                    binding.clEmail.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvEmail.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clPassword.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.ivVisibleButton.visibility = View.VISIBLE
                binding.tvPassword.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etPassword.text.isEmpty()) {
                    binding.clPassword.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvPassword.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                    binding.ivVisibleButton.visibility = View.INVISIBLE
                }
            }
        }

        binding.etPasswordConfirm.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                binding.clPasswordConfirm.setBackgroundResource(R.drawable.rectangle_orange_radius_15)
                binding.ivVisibleButtonConfirm.visibility = View.VISIBLE
                binding.tvPasswordConfirm.setTextColor(ContextCompat.getColor(this, R.color.carrot))
            }
            else {
                if(binding.etPasswordConfirm.text.isEmpty()) {
                    binding.clPasswordConfirm.setBackgroundResource(R.drawable.rectangle_gray_radius_15)
                    binding.tvPasswordConfirm.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                    binding.ivVisibleButtonConfirm.visibility = View.INVISIBLE
                }
            }
        }

    }

    private fun etAlertChange() {
        binding.etName.addTextChangedListener {
            if (LoginPatternCheckUtil.isNotValidName(binding.etName.text.toString())) {
                binding.ivAlertName.visibility = View.VISIBLE
                binding.etName.setTextColor(ContextCompat.getColor(this, R.color.mainPaleOrange))
                binding.tvAlertName.visibility = View.VISIBLE
                isValidName = false
            }
            else {
                binding.ivAlertName.visibility = View.INVISIBLE
                binding.tvAlertName.visibility = View.INVISIBLE
                binding.etName.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                isValidName = true
            }
            buttonEnabled()
        }

        binding.etEmail.addTextChangedListener {
            if (LoginPatternCheckUtil.isNotValidEmail(binding.etEmail.text.toString())) {
                binding.tvAlertEmail.visibility = View.VISIBLE
                binding.tvAlertEmail.text = "사용 불가능한 이메일이에요!"
                binding.etEmail.setTextColor(ContextCompat.getColor(this, R.color.mainPaleOrange))
                binding.tvAlertEmail.setTextColor(ContextCompat.getColor(this, R.color.carrot))
                binding.ivAlertEmail.visibility = View.VISIBLE
                isValidEmail = false
            }
            else {
                binding.tvAlertEmail.text = "입력한 이메일로 본인확인 메일이 전송 됩니다"
                binding.ivAlertEmail.visibility = View.INVISIBLE
                binding.tvAlertEmail.setTextColor(ContextCompat.getColor(this, R.color.mainGray))
                binding.etEmail.setTextColor(ContextCompat.getColor(this, R.color.mainBlack))
                isValidEmail = true
            }
            buttonEnabled()
        }

        binding.etPassword.addTextChangedListener {
            if (LoginPatternCheckUtil.isNotValidPassword(binding.etPassword.text.toString())) {
                isValidPassword = false
                binding.ivAlertPassword.visibility = View.VISIBLE
                binding.tvPasswordAlert.visibility = View.VISIBLE
                binding.tvPasswordAlert.text = "비밀번호 조합이 틀려요!"
            }
            else {
                isValidPassword = true
                binding.ivAlertPassword.visibility = View.INVISIBLE
                binding.tvPasswordAlert.visibility = View.INVISIBLE
                if(!LoginPatternCheckUtil.isPasswordCheckSuccess(binding.etPassword.text.toString(), binding.etPasswordConfirm.text.toString())) {
                    binding.ivAlertPassword.visibility = View.VISIBLE
                    binding.tvPasswordAlert.visibility = View.VISIBLE
                    binding.tvPasswordAlert.text = "비밀번호가 서로 맞지 않아요!"
                    passwordSame = false
                }
                else {
                    binding.ivAlertPassword.visibility = View.INVISIBLE
                    binding.tvPasswordAlert.visibility = View.INVISIBLE
                    passwordSame = true
                }
            }
            buttonEnabled()
        }

        binding.etPasswordConfirm.addTextChangedListener {
            if(isValidPassword) {
                if(!LoginPatternCheckUtil.isPasswordCheckSuccess(binding.etPassword.text.toString(), binding.etPasswordConfirm.text.toString())) {
                    binding.ivAlertPassword.visibility = View.VISIBLE
                    binding.tvPasswordAlert.visibility = View.VISIBLE
                    binding.tvPasswordAlert.text = "비밀번호가 서로 맞지 않아요!"
                    passwordSame = false
                }
                else {
                    binding.ivAlertPassword.visibility = View.INVISIBLE
                    binding.tvPasswordAlert.visibility = View.INVISIBLE
                    passwordSame = true
                }
                buttonEnabled()
            }
        }
    }

    private fun visibleButtonChange() {
        binding.ivVisibleButton.setOnClickListener {
            if(passwordIsVisible) {
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_visible_button)
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etPassword.letterSpacing = 0.0F
                passwordIsVisible = false
            }
            else {
                binding.ivVisibleButton.setBackgroundResource(R.drawable.ic_invisible_button)
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etPassword.letterSpacing = 0.2F
                passwordIsVisible = true
            }
        }

        binding.ivVisibleButtonConfirm.setOnClickListener {
            if(passwordConfirmIsVisible) {
                binding.ivVisibleButtonConfirm.setBackgroundResource(R.drawable.ic_visible_button)
                binding.etPasswordConfirm.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etPasswordConfirm.letterSpacing = 0.0F
                passwordConfirmIsVisible = false
            }
            else {
                binding.ivVisibleButtonConfirm.setBackgroundResource(R.drawable.ic_invisible_button)
                binding.etPasswordConfirm.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etPasswordConfirm.letterSpacing = 0.2F
                passwordConfirmIsVisible = true
            }
        }
    }

    private fun navigationClickEvent() {
        binding.tbSignUp.setNavigationOnClickListener {
            finish()
        }
    }

    private fun buttonEnabled() {
         binding.btnSignUp.isEnabled = isValidPassword && isValidEmail && isValidName && passwordSame
    }

    private fun signUpButtonClickEvent() {
        binding.btnSignUp.setOnClickListener {
            finish()
        }
    }
}