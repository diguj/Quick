package com.core.cwtailor.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.cwtailor.MainActivity;
import com.core.cwtailor.R;
import com.core.cwtailor.Selection;
import com.core.cwtailor.model.SelectionModel;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.SelectionViewHolder> {

    private Context Ctx;
    //we are storing all the products in a list
    private List<SelectionModel> optionlist;

    public SelectionAdapter(Context ctx, List<SelectionModel>optionlist){
        this.Ctx=ctx;
        this.optionlist=optionlist;
    }

    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.stichingoptions,null);
        return new SelectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, final int i) {

        final SelectionModel select = optionlist.get(i);

        holder.title.setText(select.getTitle());
        Picasso.get().load(select.getImage()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (i){
                    case 0:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mSBuilder = new AlertDialog.Builder(Ctx);
                        LayoutInflater inflater = LayoutInflater.from(Ctx);
                        View mView = inflater.inflate(R.layout.stichingoptions, null);
                        mSBuilder.setView(mView);
                        AlertDialog adailog = mSBuilder.create();
                        adailog.show();
                        break;

                    case 1:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Ctx);
                        LayoutInflater minflater = LayoutInflater.from(Ctx);
                        View mmView = minflater.inflate(R.layout.stichingoptions, null);
                        mBuilder.setView(mmView);
                        AlertDialog aadailog = mBuilder.create();
                        aadailog.show();
                        break;
                    case 2:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mnBuilder = new AlertDialog.Builder(Ctx);
                        LayoutInflater innflater = LayoutInflater.from(Ctx);
                        View mVview = innflater.inflate(R.layout.stichingoptions, null);
                        mnBuilder.setView(mVview);
                        AlertDialog addailog = mnBuilder.create();
                        addailog.show();
                        break;
                    case 3:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder nmBuilder = new AlertDialog.Builder(Ctx);
                        LayoutInflater iinflater = LayoutInflater.from(Ctx);
                        View nmView = iinflater.inflate(R.layout.stichingoptions, null);
                        nmBuilder.setView(nmView);
                        AlertDialog adaailog = nmBuilder.create();
                        adaailog.show();
                        break;
                    case 4:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mBuilderr = new AlertDialog.Builder(Ctx);
                        LayoutInflater inflaterr = LayoutInflater.from(Ctx);
                        View mVieww = inflaterr.inflate(R.layout.stichingoptions, null);
                        mBuilderr.setView(mVieww);
                        AlertDialog adailogg = mBuilderr.create();
                        adailogg.show();
                        break;
                    case 5:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mBuildeer = new AlertDialog.Builder(Ctx);
                        LayoutInflater inflateer = LayoutInflater.from(Ctx);
                        View mVieew = inflateer.inflate(R.layout.stichingoptions, null);
                        mBuildeer.setView(mVieew);
                        AlertDialog adaillog = mBuildeer.create();
                        adaillog.show();
                        break;
                    case 6:
                        intent = new Intent(Ctx, Selection.class);
                        AlertDialog.Builder mBuillder = new AlertDialog.Builder(Ctx);
                        LayoutInflater inflaater = LayoutInflater.from(Ctx);
                        View mVieeew = inflaater.inflate(R.layout.stichingoptions, null);
                        mBuillder.setView(mVieeew);
                        AlertDialog adaaillog = mBuillder.create();
                        adaaillog.show();
                        break;


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class SelectionViewHolder extends RecyclerView.ViewHolder {

         TextView title;
        //TextView title;
        ImageView imageView;
        CardView cardView;
        public SelectionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_stiching_type);

           imageView = itemView.findViewById(R.id.img_Stiching_type);

            cardView = itemView.findViewById(R.id.Stiching_options_card);
        }
    }
}
