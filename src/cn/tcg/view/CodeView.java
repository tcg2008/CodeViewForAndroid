/**
 * 
 * @author zzz
 * 创建于 2017年5月5日 下午10:53:18
 */
package cn.tcg.view;

import com.jecelyin.editor.v2.highlight.jedit.StyleLoader;
import com.jecelyin.editor.v2.ui.Document;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 *
 */
public class CodeView extends TextView
{
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
		super(context, attrs );
		StyleLoader.loadStyles(context, attrs  );
	}

	 

	@Override
	public void setText(CharSequence text, BufferType type)
	{
		SpannableStringBuilder sb = new SpannableStringBuilder(text);
		Document doc = new Document(this.getContext(), "Java");
		doc.highlight(sb);
		super.setText(sb, type);
	}

}
