package com.va181.yogamausadi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.va181.yogamausadi.InputBukuActivity;
import com.va181.yogamausadi.R;
import com.va181.yogamausadi.model.Buku;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {
    private List<Buku> dataBuku;
    private Context ctx;

    public BukuAdapter(List<Buku> dataBuku, Context ctx){
        this.dataBuku =dataBuku;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public BukuAdapter.BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.list_buku,parent,false);
        BukuViewHolder holder = new BukuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BukuAdapter.BukuViewHolder holder, int position) {
        Buku tempBuku = dataBuku.get(position);
        holder.tv_nama.setText(tempBuku.getNama_buku());
        holder.tv_pengarang.setText(tempBuku.getPengarang_buku());
        holder.tv_penerbit.setText(tempBuku.getPenerbit_buku());
        holder.tv_tahun.setText(tempBuku.getTahun_terbit());
        holder.tv_deskripsi.setText(tempBuku.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return dataBuku.size();
    }



    public class BukuViewHolder extends RecyclerView.ViewHolder implements  View.OnLongClickListener {
        private int id_buku;
    //    private String jenis_buku;
        private TextView tv_nama, tv_pengarang, tv_penerbit, tv_tahun, tv_deskripsi;
        private ImageView img_buku;
        public BukuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama         =itemView.findViewById(R.id.tv_nama);
            tv_pengarang    =itemView.findViewById(R.id.tv_pengarang);
            tv_penerbit     =itemView.findViewById(R.id.tv_penerbit);
            tv_tahun        =itemView.findViewById(R.id.tv_tahun);
            tv_deskripsi    =itemView.findViewById(R.id.tv_deskripsi);
            img_buku        =itemView.findViewById(R.id.img_buku);

   //     itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        }

       // @Override
       // public void onClick(View v) {
        //    Intent bukaTampil = new Intent(ctx, .class);

          //  bukaTampil.putExtra("NAMA BUKU", tv_nama +" "+ tv_nama);
            //bukaTampil.putExtra("PENGARANG", tv_pengarang.getText());
           // bukaTampil.putExtra("PENERBIT", tv_penerbit.getText());
           // bukaTampil.putExtra("TAHUN", tv_tahun.getContentDescription());
           // bukaTampil.putExtra("DESKRIPSI", tv_deskripsi.getText());
           // itemView.getContext().startActivity(bukaTampil);
           // ctx.startActivity(bukaTampil);
       // }

        @Override
        public boolean onLongClick(View v) {
            Intent editBuku = new Intent(ctx, InputBukuActivity.class);
            editBuku.putExtra("OPERASI","update");
            editBuku.putExtra("ID",id_buku);
            editBuku.putExtra("NAMA", tv_nama.getText().toString());
            editBuku.putExtra("PENGARANG", tv_pengarang.getText().toString());
            editBuku.putExtra("PENERBIT", tv_penerbit.getText().toString());
            editBuku.putExtra("TAHUN", tv_tahun.getText().toString());
            editBuku.putExtra("DESKRIPSI", tv_deskripsi.getText().toString());

            ctx.startActivity(editBuku);
            return false;
        }
    }
}
