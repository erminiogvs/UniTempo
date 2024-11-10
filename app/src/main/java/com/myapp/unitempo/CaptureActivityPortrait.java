package com.myapp.unitempo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;

import java.util.List;

public class CaptureActivityPortrait extends AppCompatActivity {

    private BarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_activity_layout);

        barcodeView = findViewById(R.id.barcode_view);

        // Voltar para a tela anterior
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Escaneamento
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Toast.makeText(CaptureActivityPortrait.this, "QR Code: " + result.getText(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retoma o escaneamento
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausa o escaneamento
        barcodeView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Finaliza o escaneamento
        barcodeView.pause();
    }
}