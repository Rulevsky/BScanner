//package com.example.bscanner
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.ImageProxy
//import com.google.mlkit.vision.barcode.BarcodeScannerOptions
//import com.google.mlkit.vision.barcode.BarcodeScanning
//import com.google.mlkit.vision.barcode.common.Barcode
//import com.google.mlkit.vision.common.InputImage
//import java.nio.ByteBuffer
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//class TrryAnalyzeImage(private val listener: LumaListener) : ImageAnalysis.Analyzer {
//
//
//    private fun ByteBuffer.toByteArray(): ByteArray {
//        rewind()    // Rewind the buffer to zero
//        val data = ByteArray(remaining())
//        get(data)   // Copy the buffer into a byte array
//        return data // Return the byte array
//    }
//
//
//    @SuppressLint("UnsafeOptInUsageError")
//    override fun analyze(imageProxy: ImageProxy) {
//        val buffer = imageProxy.planes[0].buffer
//        val data = buffer.toByteArray()
//        val pixels = data.map { it.toInt() and 0xFF }
//        val pohuiDouble = pixels.average()
//
//        val imageFromBB = InputImage.fromByteBuffer(
//            buffer,
//            /* image width */ 480,
//            /* image height */ 360,
//            imageProxy.imageInfo.rotationDegrees,
//            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
//        )
//
//
//
//        val options = BarcodeScannerOptions.Builder()
//            .setBarcodeFormats(
//                Barcode.FORMAT_QR_CODE,
//                Barcode.FORMAT_AZTEC
//            )
//
//            .build()
//
//        val mediaImage = imageProxy.image
//        if (mediaImage != null) {
//            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
//            // Pass image to an ML Kit Vision API
//            val scanner = BarcodeScanning.getClient(options)
//
//            val result = scanner.process(imageFromBB)
//                .addOnSuccessListener { barcodes ->
//                   Log.e("tranalize", barcodes.size.toString())
//
//                    for (barcode in barcodes) {
//                        val bounds = barcode.boundingBox
//                        val corners = barcode.cornerPoints
//                        val rawValue = barcode.rawValue
//                        val valueType = barcode.valueType
//                        Log.e("tranalize", "v" + barcode.rawValue)
//                        // See API reference for complete list of supported types
//                        when (valueType) {
//
//                            Barcode.TYPE_WIFI -> {
//                                val ssid = barcode.wifi!!.ssid
//                                val password = barcode.wifi!!.password
//                                val type = barcode.wifi!!.encryptionType
//                                Log.e("tranalize", ssid)
//                            }
//                            Barcode.TYPE_URL -> {
//                                val title = barcode.url!!.title
//                                val url = barcode.url!!.url
//                                Log.e("tranalize", url)
//                            }
//
//
//
//                        }
//
//                    }
//                }
//                .addOnFailureListener {
//                    // Task failed with an exception
//                    Log.e("tranalize", "OnFailureListener")
//                }
//
//        }
//
//
//
//
//
//        //listener(pohuiDouble)
//        imageProxy.close()
//    }
//
//}