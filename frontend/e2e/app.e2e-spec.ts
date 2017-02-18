import { MyfplUiFinalPage } from './app.po';

describe('myfpl-ui-final App', function() {
  let page: MyfplUiFinalPage;

  beforeEach(() => {
    page = new MyfplUiFinalPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
