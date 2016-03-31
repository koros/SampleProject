package abc.sampleapp.com.sampleapp.fragments;

import android.os.Bundle;

public class HomeActivityBaseFragment extends AbstractBaseFragment{
	
	public static final String ARG_PAGE = "page";
	protected int mPageNumber;
	
	public HomeActivityBaseFragment() {
		// constructor
    }
	
	public static HomeActivityBaseFragment create(int pageNumber, String genericCategory) {
		HomeActivityBaseFragment fragment = null;
		
		fragment = new HomeActivityFragment();
		
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }
	
	/**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
	
}
