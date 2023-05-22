package controller;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkService;
import hibernate.HibernateUtil;
import hibernate.bean.BookingTk;
import hibernate.impl.BookingTkServiceImpl;
import model.Utils;
import model.dto.TicketDTO;

@WebServlet("/BookingadminController")
public class BookingadminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingadminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		if (action == null) {
			action = "";
		}
		switch (action) {
		case Utils.ACTION_INSERT:
			insert(request, response);
			break;
		case "doInsert":
			doInsert(request, response);
			break;
		case Utils.ACTION_UPDATE:
			update(request, response);
			break;
		case "doUpdate":
			doUpdate(request, response);
			break;
		default:
			index(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookingDAO bookingDAO = new BookingDAOImpl();
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response); // 這裡就跳轉到新增畫面了
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void doInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();
			BookingTkService service = new BookingTkServiceImpl(session);
			BookingTk bkdto = getTicketDTOByReq(request);
			service.insert(bkdto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		/*   1. 在BookingAdmin.jsp點擊事件。
		 *   2. 跳轉BookingAdminDataPage.jsp，show 當前編輯資料。
		 * 	 3. 在BookingAdminDataPage編輯完成，跳轉回BookingAdmin.jsp頁面。
		 *   4. !!!注意參數名稱!!!
		 */
		
		try {
			String id = request.getParameter("id");
			BookingDAO bookingDAO = new BookingDAOImpl();
			BookingTk bkdto = new BookingTk((new TicketDAOImpl()).GetTicketInfoById(id));//你這邊改用 TicketDTO 比較合理一點
			// BookingTk 這個是用來"看" 的 所以你要改東西 刪東西 對某筆資料進行"操作" 就要用 TicketDTO 會比較好
			// 基本上 你也可以用 BookingTk 撈出來的東西 去個別處理其他張表 你這邊比較單純 一個view 對應一個Table
			// 就不用搞那麼麻煩
			// 不然 你應該要用 (BookingTkServiceImpl/TicketDTOServiceImpl).update(BookingTk bkdto);
			// 會比較好 (BookingTkServiceImpl/TicketDTOServiceImpl) 寫哪邊都可以 BookingTkServiceImpl 比較合理一點
			// 因為你需要透過 BookingTk 去個別處理每一張表 所以比較接近是 BookingTkServiceImpl 這個服務
			// 我想問~ 對資料除了查詢以外 是不是原本的寫法 還比較好QQ 就不要用hibernate  ?
			// 你要改用 hibernate 也可以啊  你要改 我懶得改而已  最好是一致啦  你有用hibernate 就全部都改成用hibernate
			// 好~ 但就邏輯上 我這樣使用 hibernate 的 service 對table 進行 曾改刪 是可以的嗎?
			// 可以啊  要做一遍 剛剛那個流程  拿BookingTk 的ID 透過hibernate 把 TicketDTO資料抓出來
			// 再對TicketDTO改資料 然後用 透過hibernate更新 TicketDTO物件
			
			// 有其他DTO 就做一樣的事情
			// 用 BookingTk 的ID 透過hibernate 把 DTO1/DTO2/DTO3的資料抓出來
			// 再對DTO1/DTO2/DTO3改資料 然後用 透過hibernate更新 DTO1/DTO2/DTO3物件
			// 做上面這些動作
			
			// 我有懂了QQ 因為現在用的是view 的關係
			// 職場上會很常有這種操作嗎? 有吧 你看到的東西 很多都是從 view 抓出來看得 那就需要
			
			// 去更新資料  那串就會包在BookingTkServiceImpl.update(BookingTk bkdto)裡面
			// 軒軒第一次遇到這個問題 就有這個邏輯了 還是我太笨沒想到QQ O_O
			// 你還不熟 view 是view 資料物件是資料物件  他們是兩件事
			// view物件 會有很多個資料物件  資料物件就是資料物件
			// BookingTk 是 view物件； TicketDTO 是 資料物件
			// 所以用這個邏輯看  view物件 沒有所謂的dao 都是用service去把view物件拼出來
			// 在BookingTkServiceImpl 裡面使用 各種DAO 把BookingTk 拼出來這樣可以理解嗎 XDD
			// 我也是現在想到這個問題 你剛才在弄得時候 我有感受到惹XD
			// 然後再問你 跟你說的那三句 有更懂惹!! :D
			// 你有空可以改一下 把BookingDAOImpl 通通砍掉 留BookingTkServiceImpl 就好
			// 可是 老師說要有dao 跟一個 service 耶oao 說這樣比較"彈性" XD 好吧我在感受一下XDDDD
			// 我畫個徒給妳看 你先弄其他東西 好~wee
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.setAttribute("bkdto", bkdto);
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		
	}
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();
			BookingTkService service = new BookingTkServiceImpl(session);
			BookingTk bkdto = getTicketDTOByReq(request);
			service.update(bkdto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private BookingTk getTicketDTOByReq(HttpServletRequest request) {
		BookingTk bookingTk = new BookingTk();
		String ticketID = request.getParameter("ticketID");
		bookingTk.setTicketID(Integer.parseInt(ticketID));
		String tranNo = request.getParameter("tranNo");
		bookingTk.setTranNo(tranNo);
		String seat = request.getParameter("seat");
		bookingTk.setSeat(seat);
		String departureST = request.getParameter("departureST");
		bookingTk.setDepartureST(departureST);
		String destinationST = request.getParameter("destinationST");
		bookingTk.setDestinationST(destinationST);
		String depturedate = request.getParameter("depturedate");
		bookingTk.setdeparturedate(depturedate);
		String departuretime = request.getParameter("departuretime");
		bookingTk.setDeparturetime(departuretime);
		String arrivaltime = request.getParameter("arrivaltime");
		bookingTk.setArrivaltime(arrivaltime);
		String price = request.getParameter("price"); 
		bookingTk.setPrice(Integer.parseInt(price));
		String bookingdate = request.getParameter("bookingdate");
		bookingTk.setbookingdate(bookingdate);
		return bookingTk;
	}
}
