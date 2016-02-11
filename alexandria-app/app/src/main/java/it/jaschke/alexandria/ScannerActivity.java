package it.jaschke.alexandria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by hakalamarek on 06/11/15.
 */
public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    protected ZXingScannerView scannerView;
    public static final String EXTRA_BAR_CODE = "barCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(this);
        setContentView(scannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_BAR_CODE, result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
