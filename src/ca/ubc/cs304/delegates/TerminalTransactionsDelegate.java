package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.Branch;
import ca.ubc.cs304.model.CustomerModel;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	public void deleteBranch(Branch model);
	public void insertBranch(Branch model);
	public void showBranch();
	
	public void terminalTransactionsFinished();

	public void addCustomerDetails(CustomerModel customerModel);
}
