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

package com.jecelyin.editor.v2.ui;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan; 
import com.jecelyin.editor.v2.highlight.Buffer;
import com.jecelyin.editor.v2.highlight.HighlightInfo;
import com.jecelyin.editor.v2.highlight.jedit.StyleLoader;
import com.jecelyin.editor.v2.highlight.jedit.modes.Catalog;
import com.jecelyin.editor.v2.highlight.jedit.syntax.DefaultTokenHandler;
import com.jecelyin.editor.v2.highlight.jedit.syntax.SyntaxStyle;
import com.jecelyin.editor.v2.highlight.jedit.syntax.Token;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */
public class Document
{
	public static SyntaxStyle[] styles;

	private final Context context;
	private final Buffer buffer;
	private final HashMap<Integer, ArrayList<ForegroundColorSpan>> colorSpanMap;

	public Document(Context contex,String modeName)
	{
		this.context = contex;
		buffer = new Buffer(context);
		colorSpanMap = new HashMap<>();
		buffer.setMode(Catalog.getModeByName(modeName));
	}

	public void highlight(Spannable spannableStringBuilder)
	{ 
		highlight(spannableStringBuilder, 0, -1);
	}

	public void highlight(Spannable spannableStringBuilder, int startLine,
			int endLine)
	{
		buffer.insert(0, spannableStringBuilder);
		int count = buffer.getLineManager().getLineCount(); 
		if (endLine == -1)
		{
			endLine = count - 1;
		}
		DefaultTokenHandler tokenHandler;

		if (styles == null)
			styles = StyleLoader.loadStyles(context);
		ArrayList<HighlightInfo> mergerArray;

		for (int i = startLine; i <= endLine; i++)
		{
			tokenHandler = new DefaultTokenHandler();
			buffer.markTokens(i, tokenHandler);
			Token token = tokenHandler.getTokens();

			mergerArray = new ArrayList<>();
			collectToken(buffer, i, token, mergerArray);
			addTokenSpans(spannableStringBuilder, i, mergerArray);
		}

	}

	private void addTokenSpans(Spannable spannableStringBuilder, int line,
			ArrayList<HighlightInfo> mergerArray)
	{
		ForegroundColorSpan fcs;

		ArrayList<ForegroundColorSpan> oldSpans = colorSpanMap.remove(line);
		if (oldSpans != null)
		{
			for (ForegroundColorSpan span : oldSpans)
			{
				spannableStringBuilder.removeSpan(span);
			}
		}

		int length = spannableStringBuilder.length();

		ArrayList<ForegroundColorSpan> spans = new ArrayList<>(
				mergerArray.size());
		for (HighlightInfo hi : mergerArray)
		{
			if (hi.endOffset > length)
			{
				// TODO: 15/12/27 不应该出现这种情况，要找到原因并解决
				hi.endOffset = length;
			}
			if (hi.startOffset >= hi.endOffset)
			{
				continue;
			}
			fcs = new ForegroundColorSpan(hi.color);
 
			spannableStringBuilder.setSpan(fcs, hi.startOffset, hi.endOffset,
					SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
			spans.add(fcs);
		}
		colorSpanMap.put(line, spans);
	}

	private void collectToken(Buffer buffer, int lineNumber, Token token,
			ArrayList<HighlightInfo> mergerArray)
	{
		// Segment segment = new Segment();
		// buffer.getLineText(lineNumber, segment);
		// String line = segment.toString();
		// String match;

		int lineStartOffset = buffer.getLineManager()
				.getLineStartOffset(lineNumber);

		HighlightInfo hi;
		while (token.id != Token.END)
		{
			int startIndex = lineStartOffset + token.offset;
			int endIndex = lineStartOffset + token.offset + token.length;
			SyntaxStyle style = styles[token.id];
			// 注意下面这句的使用
			token = token.next;

			if (style == null)
				continue;

			// int color = 0xFFFFFF & style.getForegroundColor();
			int color = style.getForegroundColor();

			if (mergerArray.isEmpty())
			{
				mergerArray.add(new HighlightInfo(startIndex, endIndex, color));
			} else
			{
				hi = mergerArray.get(mergerArray.size() - 1);
				if (hi.color == color && hi.endOffset == startIndex)
				{
					hi.endOffset = endIndex;
				} else
				{
					mergerArray.add(
							new HighlightInfo(startIndex, endIndex, color));
				}
			}
		}

		// for(HighlightInfo hl : mergerArray) {
		// match = line.substring(hl.startOffset, hl.endOffset);
		// System.err.println("<" + String.format("#%06X", hl.color) + ">" +
		// match + "</" + String.format("#%06X", hl.color) + ">");
		// }

	}
}
