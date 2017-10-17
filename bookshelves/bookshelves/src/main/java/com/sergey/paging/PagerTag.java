package com.sergey.paging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PagerTag extends SimpleTagSupport {

    @SuppressWarnings("unchecked")
    private ListPage page;

    private String pageParam;

    public String getPageParam() {
        return pageParam;
    }

    public void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }

    @SuppressWarnings("unchecked")
    public ListPage getPage() {
        return page;
    }

    @SuppressWarnings("unchecked")
    public void setPage(ListPage page) {
        this.page = page;
    }

    @Override
    public void doTag() throws JspException, IOException {
        StringBuffer requestURL = getRequestUrl();

        int pagesTotal = page.getTotal() / page.getMaxPerPage();
        if (page.getTotal() % page.getMaxPerPage() != 0) {
            pagesTotal++;
        }

        if (pagesTotal > 1) {
            int startOfPageParam = requestURL.indexOf(pageParam + "=");
            if (startOfPageParam != -1) {
                int endOfPageParam = requestURL.indexOf("&", startOfPageParam + pageParam.length());
                if (endOfPageParam == -1) {
                    endOfPageParam = requestURL.length();
                }
                requestURL.delete(startOfPageParam, endOfPageParam);
            }
            char lastQueryChar = requestURL.charAt(requestURL.length() - 1);
            if (lastQueryChar != '?' && lastQueryChar != '&') {
                requestURL.append('&');
            }
            requestURL.append(pageParam).append('=');
            String url = requestURL.toString();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pagesTotal; i++) {
                if (page.getPage() != i) {
                    sb.append("<a href=\"").append(url).append(i).append("\">");
                    sb.append(i + 1);
                    sb.append("</a> ");
                } else {
                    sb.append("<b>");
                    sb.append(i + 1);
                    sb.append("</b> ");
                }
            }

            getJspContext().getOut().append(sb.toString());
        }
    }

    private StringBuffer getRequestUrl() {
        StringBuffer construct = construct(getJspContext(), "javax.servlet.forward.");
        if (construct == null) {
            construct = construct(getJspContext(), "javax.servlet.include.");
        }
        if (construct == null) {
            PageContext ctx = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
            construct = request.getRequestURL();
            construct.append('?');
            if (request.getQueryString() != null) {
                construct.append(request.getQueryString());
            }
        }
        return construct;
    }

    private StringBuffer construct(JspContext context, String prefix) {
        Object uri = context.getAttribute(prefix + "request_uri", PageContext.REQUEST_SCOPE);
        if (uri != null) {
            Object ctxPath = context.getAttribute(prefix + "context_path", PageContext.REQUEST_SCOPE);
            Object servletPath = context.getAttribute(prefix + "servlet_path", PageContext.REQUEST_SCOPE);
            Object pathInfo = context.getAttribute(prefix + "path_info", PageContext.REQUEST_SCOPE);
            Object query = context.getAttribute(prefix + "query_string", PageContext.REQUEST_SCOPE);
            StringBuffer buffer = new StringBuffer();
            buffer.append(ctxPath).append(servletPath);
            if (pathInfo != null) {
                buffer.append(pathInfo);
            }
            buffer.append('?');
            if (query != null) {
                buffer.append(query);
            }
            return buffer;
        }
        return null;
    }

}
