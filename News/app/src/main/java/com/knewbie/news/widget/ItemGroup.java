package com.knewbie.news.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knewbie.news.R;

public class ItemGroup extends FrameLayout implements View.OnClickListener {//

    private LinearLayout itemGroupLayout;
    private TextView textViewTitle;
    private TextView textViewContent;
    //private EditText editTextContent;
    private ImageView imageViewClear;
    private ImageView imageViewNextChoice;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ItemGroup(Context context) {
        super(context);
        initView(context);
    }

    public ItemGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public ItemGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }

    /*public ItemGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }*/

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_layout, null);
        itemGroupLayout = view.findViewById(R.id.item_group_layout);
        textViewTitle = view.findViewById(R.id.textView_title);
        textViewContent = view.findViewById(R.id.textView_content);
        //editTextContent = view.findViewById(R.id.editText_content);
        imageViewClear = view.findViewById(R.id.imageView_clear);
        imageViewNextChoice = view.findViewById(R.id.imageView_nextChoice);
        addView(view);

        //editTextContent.setFocusable(false);
        itemGroupLayout.setOnClickListener(this);

        /*editTextContent.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v);
                }
                return false;
            }
        });*/
        imageViewClear.setOnClickListener(this);
        /*editTextContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editTextContent.getText().toString();
                if (text != null && !text.equals("")) {
                    imageViewClear.setVisibility(VISIBLE);
                } else imageViewClear.setVisibility(GONE);
            }
        });*/
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        int defaultTitleColor = context.getResources().getColor(android.R.color.black);
        int defaultContentColor = context.getResources().getColor(android.R.color.darker_gray);
        //int defaultHintColor = context.getResources().getColor(android.R.color.darker_gray);


        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ItemGroup);
        String title = typedArray.getString(R.styleable.ItemGroup_title);
        float paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 5);
        float paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 5);
        float paddingTop = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        float paddingBottom = typedArray.getDimension(R.styleable.ItemGroup_paddingBottom, 5);
        float titleSize = typedArray.getDimension(R.styleable.ItemGroup_title_size, 15);
        int titleColor = typedArray.getColor(R.styleable.ItemGroup_title_color, defaultTitleColor);

        String content = typedArray.getString(R.styleable.ItemGroup_content_text);
        float contentSize = typedArray.getDimension(R.styleable.ItemGroup_content_size, 13);
        int contentColor = typedArray.getColor(R.styleable.ItemGroup_content_color, defaultContentColor);
        /*
        String content = typedArray.getString(R.styleable.ItemGroup_content_text);
        float contentSize = typedArray.getDimension(R.styleable.ItemGroup_content_size, 13);
        int contentColor = typedArray.getColor(R.styleable.ItemGroup_content_color, defaultContentColor);
        String hintContent = typedArray.getString(R.styleable.ItemGroup_editHint_content);
        int hintColor = typedArray.getColor(R.styleable.ItemGroup_editHint_color, defaultHintColor);
        boolean isEditable = typedArray.getBoolean(R.styleable.ItemGroup_editText_isEditable, false);
        */
        boolean nextPicVisible = typedArray.getBoolean(R.styleable.ItemGroup_nextChoice_visible, true);
        typedArray.recycle();

        itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
        textViewTitle.setText(title);
        textViewTitle.setTextSize(titleSize);
        textViewTitle.setTextColor(titleColor);
        textViewContent.setText(content);
        textViewContent.setTextSize(contentSize);
        textViewContent.setTextColor(contentColor);
        /*
        editTextContent.setText(content);
        editTextContent.setTextSize(contentSize);
        editTextContent.setTextColor(contentColor);
        editTextContent.setHint(hintContent);
        editTextContent.setHintTextColor(hintColor);
        editTextContent.setFocusableInTouchMode(isEditable);    //输入框是否可编辑
        editTextContent.setLongClickable(false);
        */
        imageViewNextChoice.setVisibility(nextPicVisible ? View.VISIBLE : View.GONE);
    }

    public void setText(String text) {
        textViewContent.setText(text);
        //editTextContent.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_group_layout:
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(this);
                }
                break;
            /*
            case R.id.imageView_clear:
                editTextContent.setText("");
                imageViewClear.setVisibility(GONE);
                break;
            */
        }
    }
}