package cd.acgt.acgtexp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cd.acgt.acgtexp.R;

/**
 * Created by Sugar on 5/30/2018
 */
public class SelectedPhotoAdapter extends RecyclerView.Adapter<SelectedPhotoAdapter.ViewHolder>{

    private Context mContext;
    private List<Uri> imageUris = new ArrayList<>();
    private List<String> photoPaths = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIV;

        ViewHolder(View view){
            super(view);
            imageIV = view.findViewById(R.id.image_iv);
        }
    }

    public SelectedPhotoAdapter(Context context) {
        this.mContext = context;
    }

    public void addPhotoPaths(List<String> paths) {
        this.photoPaths.addAll(paths);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View photoView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_photo_item, parent, false);

        return new ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoPaths.get(position));

        holder.imageIV.setImageBitmap(bitmap);
    }

    public List<Uri> getImageUris(){
        return this.imageUris;
    }

    public List<String> getImagePaths() {return this.photoPaths; }

    @Override
    public int getItemCount() {
        return photoPaths.size();
    }


}
