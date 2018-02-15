package org.engim.gtt;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class HomePage extends WebPage
{

  private static final long serialVersionUID = 1L;

  public HomePage(final PageParameters parameters)
  {
    super(parameters);
    
    BookmarkablePageLink linee =
      new BookmarkablePageLink("linklinee", 
                              Linee.class);
    add(linee);
    
    BookmarkablePageLink fermate =
      new BookmarkablePageLink("linkfermate", 
                              Fermate.class);
    add(fermate);
    
  }
}
