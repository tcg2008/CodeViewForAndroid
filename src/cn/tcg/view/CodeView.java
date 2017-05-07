/**
 * 
 * @author zzz
 * 创建于 2017年5月5日 下午10:53:18
 */
package cn.tcg.view;

import com.jecelyin.editor.v2.highlight.jedit.StyleLoader;
import com.jecelyin.editor.v2.ui.Document;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import cn.tcg.view.codeview.R;

/**
 * 
 *
 */
public class CodeView extends TextView
{
	private String name ;
	/**
	 * 
	 */
	public CodeView(Context context)
	{
		super(context);
	}

	/**
	 * 
	 */
	public CodeView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	/**
	 * 
	 */
	public CodeView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs,defStyleAttr );
		TypedArray a = null;
		try
		{
			a = context.obtainStyledAttributes(attrs, R.styleable.CodeView);
			if(a.hasValue(R.styleable.CodeView_mode))
			{
				name = a.getString(R.styleable.CodeView_mode);
			}
		} finally
		{
			a.recycle();
		}
		StyleLoader.loadStyles(context, attrs  );
	}

	/**
	 *
	 * @see com.jecelyin.editor.v2.highlight.jedit.modes.Catalog#modes
	 * @param name
	 */
	public void setMode(String name)
	{
		this.name = name;
	}

	@Override
	public void setText(CharSequence text, BufferType type)
	{
		if(name==null)
		{
			name="Java";
		}
		SpannableStringBuilder sb = new SpannableStringBuilder(text);
		Document doc = new Document(this.getContext(), name);
		doc.highlight(sb);
		super.setText(sb, type);
	}

}
