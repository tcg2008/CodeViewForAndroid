package cn.tcg.view.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import java.io.Closeable;

/**
 * layout属性值>自定义样式>应用主题样式>默认值
 * @author zzz
 * 创建于 2017年5月5日 下午10:55:18
 */
public class TypedArrayUtils implements Closeable
{
	/**
	 * 默认样式
	 */
	TypedArray a = null;
	/**
	 * 主题样式(自定义>应用),优选
	 */
	TypedArray b = null;
	/**
	 *
	 * @param context
	 * @param set
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	private TypedArrayUtils(Context context,
							AttributeSet set, int[] attrs, int defStyleAttr,
							int defStyleRes)
	{
		//默认样式 
		a = context.obtainStyledAttributes(defStyleRes, attrs) ; 
		//应用样式,不获取默认值
		b = context.obtainStyledAttributes(set, attrs,defStyleAttr,0);
	 
	}

	public final static TypedArrayUtils obtainStyledAttributes(Context context,
															   AttributeSet set, int[] attrs, int defStyleAttr,
															   int defStyleRes)
	{
		return new TypedArrayUtils(context,
								   set, attrs, defStyleAttr, defStyleRes);
	}

	public int length()
	{
		return b.length();
	}


	public int getIndexCount()
	{
		return b.getIndexCount();
	}


	public int getIndex(int at)
	{
		return b.getIndex(at);
	}


	public Resources getResources()
	{
		return b.getResources();
	}


	public CharSequence getText(int index)
	{
		if(b.hasValue(index))
		{
			return b.getText(index);
		}
		return a.getText(index);
	}


	public String getString(int index)
	{
		if(b.hasValue(index))
		{
			return b.getString(index);
		}
		return a.getString(index);
	}


	public String getNonResourceString(int index)
	{
		if(b.hasValue(index))
		{
			return b.getNonResourceString(index);
		}
		return a.getNonResourceString(index);
	}


	public boolean getBoolean(int index, boolean defValue)
	{
		if(b.hasValue(index))
		{
			return b.getBoolean(index,defValue);
		}
		return a.getBoolean(index, defValue);
	}


	public int getInt(int index, int defValue)
	{
		return b.getInt(index, a.getInt(index,defValue));
	}


	public float getFloat(int index, float defValue)
	{
		return b.getFloat(index, a.getFloat(index,defValue));
	}


	public int getColor(int index, int defValue)
	{
		return b.getColor(index, a.getColor(index,defValue));
	}


	public ColorStateList getColorStateList(int index)
	{
		if(b.hasValue(index))
		{
			return b.getColorStateList(index);
		}
		return a.getColorStateList(index);
	}


	public int getInteger(int index, int defValue)
	{
		return b.getInteger(index, a.getInteger(index,defValue));
	}


	public float getDimension(int index, float defValue)
	{
		return b.getDimension(index, a.getDimension(index,defValue));
	}


	public int getDimensionPixelOffset(int index, int defValue)
	{
		return b.getDimensionPixelOffset(index,  a.getDimensionPixelOffset(index,defValue));
	}


	public int getDimensionPixelSize(int index, int defValue)
	{
		return b.getDimensionPixelSize(index,  a.getDimensionPixelSize(index,defValue));
	}


	public int getLayoutDimension(int index, String name)
	{
		if(b.hasValue(index))
		{
			return b.getLayoutDimension(index, name);
		}
		return a.getLayoutDimension(index, name);
	}


	public int getLayoutDimension(int index, int defValue)
	{
		return b.getLayoutDimension(index, a.getLayoutDimension(index,defValue));
	}


	public float getFraction(int index, int base, int pbase, float defValue)
	{
		return b.getFraction(index, base, pbase, a.getFraction(index,base,pbase,defValue));
	}


	public int getResourceId(int index, int defValue)
	{
		return b.getResourceId(index, a.getResourceId(index, defValue));
	}


	public Drawable getDrawable(int index)
	{
		if(b.hasValue(index))
		{
			return b.getDrawable(index);
		}
		return a.getDrawable(index);
	}


	public CharSequence[] getTextArray(int index)
	{
		if(b.hasValue(index))
		{
			return b.getTextArray(index);
		}
		return a.getTextArray(index);
	}


	public boolean getValue(int index, TypedValue outValue)
	{
		if(b.hasValue(index))
		{
			return b.getValue(index, outValue);
		}
		return a.getValue(index, outValue);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public int getType(int index)
	{
		if(b.hasValue(index))
		{
			return b.getType(index);
		}
		return a.getType(index);
	}


	public boolean hasValue(int index)
	{
		return b.hasValue(index)||a.hasValue(index);
	}

	@TargetApi(Build.VERSION_CODES.M)
	public boolean hasValueOrEmpty(int index)
	{
		return b.hasValueOrEmpty(index)||a.hasValueOrEmpty(index);
	}


	public TypedValue peekValue(int index)
	{
		if(b.hasValue(index))
		{
			return b.peekValue(index);
		}
		return a.peekValue(index);
	}


	public String getPositionDescription()
	{
		return b.getPositionDescription();
	}


	public void recycle()
	{
		if(a!=null)
		{
			a.recycle();
		}
		if(b!=null)
		{
			b.recycle();
		} 
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public int getChangingConfigurations()
	{
		return b.getChangingConfigurations();
	}


	public void close()
	{
		recycle();
	}
}
