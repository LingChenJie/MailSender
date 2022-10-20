package com.android.mailsender

import java.util.concurrent.Executors
import javax.mail.Transport

/**
 * 邮件发送器
 */
object MailSender {

    private val EXECUTOR = Executors.newSingleThreadExecutor()

    /**
     * 发送邮件
     */
    fun sendMail(mail: Mail, onMailSendListener: OnMailSendListener? = null) {
        EXECUTOR.execute {
            try {
                Transport.send(MailUtil.createMailMessage(mail))
                onMailSendListener?.onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onMailSendListener?.onError(e)
            }

        }
    }

    /**
     * 发送回调
     */
    interface OnMailSendListener {
        fun onSuccess()
        fun onError(e: Throwable)
    }
}
