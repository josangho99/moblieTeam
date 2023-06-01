package com.example.teamproject

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.teamproject.databinding.ActivityJoinmembershipBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


private var auth : FirebaseAuth? = null

class JoinmembershipActivity : Activity(){

    var db : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityJoinmembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance() //Firebase cloudstore

        binding.joinJoinbtn.setOnClickListener {
            createAccount(binding.joinId.text.toString(),binding.joinPw.text.toString())
        }
        binding.joinIdCheckBtn.setOnClickListener {

        }

    }


    private fun createAccount(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_LONG
                        ).show()
                        userData() // 가입내용 데이터베이스에 저장
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        else if(isCheckPw()){
            Toast.makeText(this,"이메일과 비밀번호를 입력하십시오.",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show()
        }
    }
    private fun isCheckPw(): Boolean { //비밀번호 확인
        var binding = ActivityJoinmembershipBinding.inflate(layoutInflater)
        if(binding.joinPwCheck.text.toString() == binding.joinPw.text.toString()){
            binding.joinPwCheckResult.visibility = View.VISIBLE
            return true
        }
        return false
    }
    fun userData() {
        var user = hashMapOf<String,String?>()
        user.put("Email", auth?.currentUser?.email)
        user.put("Uid", auth?.currentUser?.uid)
        user.put("Displayname", auth?.currentUser?.displayName)
        db?.collection("User")
            ?.add(user)
            ?.addOnSuccessListener{
                Log.i(ContentValues.TAG, "유저 데이터 저장 성공")
            }
    }

}