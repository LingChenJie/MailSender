package com.android.mailsender

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btSend = findViewById<Button>(R.id.bt_mail_send)
        btSend.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail() {
        val mail = getMail()
        MailSender.sendMail(mail, object : MailSender.OnMailSendListener {
            override fun onSuccess() {
                Log.e("MailSender", "onSuccess")
            }

            override fun onError(e: Throwable) {
                Log.e("MailSender", "onError:" + e.message)
            }
        })
    }

    private fun getMail(): Mail {
        val mail = Mail()
        mail.mailServerHost = "smtp.163.com"
        mail.mailServerPort = "25"
        mail.fromAddress = ""// 发件人邮箱地址
        mail.password = ""// 发件人邮箱授权码
        val toAddress = ArrayList<String>()
        toAddress.add("")// 收件人邮箱
        mail.toAddress = toAddress
        mail.subject = "Log Title"// 邮件主题
        mail.content = "Log Content"// 邮件内容
        return mail
    }

}