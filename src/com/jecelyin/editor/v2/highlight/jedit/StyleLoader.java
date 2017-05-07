/*
 * Copyright (C) 2016 Jecelyin Peng <jecelyin@gmail.com>
 *
 * This file is part of 920 Text Editor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jecelyin.editor.v2.highlight.jedit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import cn.tcg.view.codeview.R;
import cn.tcg.view.utils.TypedArrayUtils;
import com.jecelyin.editor.v2.highlight.jedit.syntax.SyntaxStyle;
import com.jecelyin.editor.v2.highlight.jedit.syntax.Token;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */
public class StyleLoader
{
	public static SyntaxStyle[] loadStyles(Context context)
	{
		return loadStyles(context, null);
	}

	public static SyntaxStyle[] loadStyles(Context context, AttributeSet set )
	{
	 
		TypedArrayUtils a = TypedArrayUtils.obtainStyledAttributes(context,set,
				R.styleable.Highlight, R.attr.HighlightStyle,
				R.style.HighlightStyle);  
		SyntaxStyle[] styles = new SyntaxStyle[Token.ID_COUNT];

		styles[Token.COMMENT1] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlComment1, 0), 0);
		styles[Token.COMMENT2] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlComment2, 0), 0);
		styles[Token.COMMENT3] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlComment3, 0), 0);
		styles[Token.COMMENT4] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlComment4, 0), 0);
	 
		styles[Token.DIGIT] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlDigit, 0), 0);
		styles[Token.FUNCTION] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlFunction, 0), 0);
		styles[Token.INVALID] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlInvalid, 0), 0);
		styles[Token.KEYWORD1] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlKeyword1, 0), 0);
		styles[Token.KEYWORD2] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlKeyword2, 0), 0);
		styles[Token.KEYWORD3] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlKeyword3, 0), 0);
		styles[Token.KEYWORD4] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlKeyword4, 0), 0);
		styles[Token.LABEL] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlLabel, 0), 0);
		styles[Token.LITERAL1] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlLiteral1, 0), 0);
		styles[Token.LITERAL2] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlLiteral2, 0), 0);
		styles[Token.LITERAL3] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlLiteral3, 0), 0);
		styles[Token.LITERAL4] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlLiteral4, 0), 0);
		styles[Token.MARKUP] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlMarkup, 0), 0);
		styles[Token.OPERATOR] = new SyntaxStyle(
				a.getColor(R.styleable.Highlight_hlOperator, 0), 0);

		a.recycle();

		return styles;
	}
}
