package com.example.modular.ekoworkshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

open class BaseActivity : AppCompatActivity() {
    private val tags = "BaseActivity"

    //SplitInstallManager : Responsible for downloading the module.
    private lateinit var splitInstallManager: SplitInstallManager
    //SplitInstallRequest: Contain the request information that will be used to request our dynamic feature module from Google Play
    private lateinit var request: SplitInstallRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDynamicModules()
    }

    private fun initDynamicModules() {
        splitInstallManager = SplitInstallManagerFactory.create(this)
        request = SplitInstallRequest
            .newBuilder()
            .addModule(CONTENT_DYNAMIC_FEATURE)
            .build()
    }

    fun installModule(module: String, isDownloaded: (Boolean) -> Unit) {
        if (!isDynamicFeatureDownloaded(feature = module)) {
            downloadFeature { isDownloaded.invoke(it) }
        } else isDownloaded.invoke(true)
    }

    private fun isDynamicFeatureDownloaded(feature: String): Boolean =
        splitInstallManager.installedModules.contains(feature)

    private fun downloadFeature(isDownloaded: (Boolean) -> Unit) {
        splitInstallManager.startInstall(request)
            .addOnFailureListener {
                Log.d(tags, " downloadFeature OnFailure :" + it.localizedMessage)
                isDownloaded.invoke(false)
            }
            .addOnSuccessListener {
                Log.d(tags, " downloadFeature OnSuccess : $it")
                isDownloaded.invoke(true)
            }
            .addOnCompleteListener {
                Log.d(tags, " downloadFeature OnComplete: " + it.result.toString())
                isDownloaded.invoke(true)
            }

        monitorStateRequestDynamicModule()
    }

    private fun monitorStateRequestDynamicModule() {
        //Monitor the state of the request in the process
        SplitInstallStateUpdatedListener {
            Log.d(tags, " sessionId: " + it.sessionId())
            when (it.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    val totalBytes = it.totalBytesToDownload()
                    val progress = it.bytesDownloaded()
                    // Update progress bar.
                }
                SplitInstallSessionStatus.INSTALLING -> Log.d(tags, " status: INSTALLING")
                SplitInstallSessionStatus.INSTALLED -> Log.d(tags, " status: INSTALLED")
                SplitInstallSessionStatus.FAILED -> Log.d(tags, "status: FAILED")
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> startIntentSender(
                    it.resolutionIntent().intentSender,
                    null,
                    0,
                    0,
                    0
                )
                SplitInstallSessionStatus.CANCELED -> {
                    Log.d(tags, " status: CANCELED")
                }
                SplitInstallSessionStatus.CANCELING -> {
                    Log.d(tags, " status: CANCELING")
                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    Log.d(tags, " status: DOWNLOADED")
                }
                SplitInstallSessionStatus.PENDING -> {
                    Log.d(tags, " status: PENDING")
                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    Log.d(tags, " status: UNKNOWN")
                }
            }
        }
    }

    private fun uninstallDynamicFeature(list: List<String>) {
        splitInstallManager.deferredUninstall(list)
            .addOnSuccessListener {
                //TODO Something
            }
    }

}
