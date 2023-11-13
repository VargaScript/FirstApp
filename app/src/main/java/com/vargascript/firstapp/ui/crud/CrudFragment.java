package com.vargascript.firstapp.ui.crud;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vargascript.firstapp.R;

public class CrudFragment extends Fragment {

    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_crud, container, false);

        ActivityResultLauncher<String[]> locationPermissionRequest =
        registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    if (fineLocationGranted != null && fineLocationGranted) {
                        Toast.makeText(getContext(), "Ubicacion precisa.", Toast.LENGTH_SHORT).show();
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        Toast.makeText(getContext(), "Ubicacion aproximada.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No acepto los permisos.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        return root;
    }

}