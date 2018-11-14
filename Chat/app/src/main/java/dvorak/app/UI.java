package dvorak.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class UI {

}

class Actvt extends Activity {
    private RecyclerView rView;
    private RecyclerView.Adapter adptr;
    private RecyclerView.LayoutManager layoutM;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rView = (RecyclerView) findViewById(R.id.msgs_recycler_view);

        rView.setHasFixedSize(true);

        layoutM = new LinearLayoutManager(this);
        rView.setLayoutManager(layoutM);

        adptr = new Adptr(msgs); // need to fix (fill in with dataset)
        rView.setAdapter(adptr);
    }
}

class Adptr extends RecyclerView.Adapter<Adptr.VwHldr> {
    private String[] dataset;

    public static class VwHldr extends RecyclerView.ViewHolder {
        public TextView tv;
        public VwHldr(TextView t){
            super(t);
            tv = t;
        }
    }

    public Adptr(String[] ds){
        dataset = ds;
    }

    @Override
    public Adptr.VwHldr onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main /* change this to msg view later on*/, parent, false);
        VwHldr vh = new VwHldr(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VwHldr vh, int position) {
        vh.tv.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}