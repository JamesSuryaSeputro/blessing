package com.example.blessing;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
=======
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
>>>>>>> 73837329fd07894514e9194bf88f89881d1a8dff
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

<<<<<<< HEAD
import com.example.blessing.Adapter.ImagePembahasanAdapter;
import com.example.blessing.Adapter.JawabanAdapter;
import com.example.blessing.Model.KuisModel;
import com.example.blessing.Service.API;
import com.example.blessing.Service.RetrofitBuildCustom;
import com.example.blessing.Utils.Preferences;
=======
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.blessing.Adapter.ImagePembahasanAdapter;
import com.example.blessing.Adapter.JawabanAdapter;
import com.example.blessing.Adapter.SoalAdapter;
import com.example.blessing.Model.KuisModel;
import com.example.blessing.Model.MainModel;
import com.example.blessing.Model.MapelSoalModel;
import com.example.blessing.Service.API;
import com.example.blessing.Service.RetrofitBuildCustom;
import com.example.blessing.Utils.Preferences;
import com.github.chrisbanes.photoview.PhotoView;
>>>>>>> 73837329fd07894514e9194bf88f89881d1a8dff

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembahasanActivity extends AppCompatActivity {
<<<<<<< HEAD
    public static final String EXTRA_BOOLEAN = "extra_boolean";
    public static final String EXTRA_SOAL = "extra_soal";
=======
>>>>>>> 73837329fd07894514e9194bf88f89881d1a8dff
    private static final String TAG = PembahasanActivity.class.getSimpleName();
    private ImagePembahasanAdapter mAdapter;
    private JawabanAdapter mJawabanAdapter;
    private API service;
<<<<<<< HEAD
=======
    public static final String EXTRA_BOOLEAN = "extra_boolean";
    public static final String EXTRA_SOAL = "extra_soal";
>>>>>>> 73837329fd07894514e9194bf88f89881d1a8dff
    private String idsoal;
    private TextView tvJawaban, tvBelumAdaPembahasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembahasan);

        String namasoal = Preferences.getKeyNamaSoal(getBaseContext());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> " + namasoal + " </font>", HtmlCompat.FROM_HTML_MODE_LEGACY));

        SnapHelper helper = new LinearSnapHelper();
        RecyclerView rvImage = findViewById(R.id.RV_imgpembahasan);
        rvImage.setHasFixedSize(true);
        helper.attachToRecyclerView(rvImage);
        rvImage.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ImagePembahasanAdapter(new ArrayList<>(), PembahasanActivity.this);
        rvImage.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (mAdapter.getItemCount() == 0) {
                    tvJawaban.setVisibility(View.GONE);
                    tvBelumAdaPembahasan.setVisibility(View.VISIBLE);
                }
            }
        });

        RecyclerView rvJawaban = findViewById(R.id.RV_jawaban);
        rvJawaban.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mJawabanAdapter = new JawabanAdapter(new ArrayList<>(), PembahasanActivity.this);
        rvJawaban.setAdapter(mJawabanAdapter);

        service = RetrofitBuildCustom.getInstance().getService();
        idsoal = Preferences.getKeyIdSoal(getBaseContext());
        tvJawaban = findViewById(R.id.tvjawaban);
        tvBelumAdaPembahasan = findViewById(R.id.tvbelumadapembahasan);

        getPembahasan(idsoal);
    }

    public void getPembahasan(String id) {
        service.getdetailkuisbysoal(id).enqueue(new Callback<List<KuisModel>>() {
            @Override
            public void onResponse(Call<List<KuisModel>> call, Response<List<KuisModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mAdapter.updateData(response.body());
                        mJawabanAdapter.updateData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<KuisModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(PembahasanActivity.this, SoalActivity.class);
            intent.putExtra(EXTRA_BOOLEAN, true);
            intent.putExtra(EXTRA_SOAL, idsoal);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}