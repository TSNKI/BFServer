//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub

		// Transfer code into an ArrayList.
		char[] codes = code.toCharArray();

		// Transfer param into characters.
		char[] params = param.toCharArray();

		int[] stack = new int[1024];
		int stackPtr = 0;
		ArrayList<Integer> loopPtr = new ArrayList<Integer>();
		int paramPtr = 0;
		int codePtr = 0;
		String res = "";

		while (codePtr < codes.length) {
			switch (codes[codePtr]) {
			case ',':
				stack[stackPtr] = params[paramPtr];
				paramPtr++;
				codePtr++;
				break;
			case '.':
				res += (char) stack[stackPtr];
				codePtr++;
				break;
			case '+':
				stack[stackPtr]++;
				codePtr++;
				break;
			case '-':
				stack[stackPtr]--;
				codePtr++;
				break;
			case '>':
				stackPtr++;
				codePtr++;
				break;
			case '<':
				stackPtr--;
				codePtr++;
				break;
			case '[':
				if (stack[stackPtr] == 0) {
					codePtr++;
					while (codes[codePtr] != ']') {
						codePtr++;
					}
					codePtr++;
				} else {
					loopPtr.add(codePtr);
					codePtr++;
				}
				break;
			case ']':
				if (stack[stackPtr] != 0) {
					codePtr = loopPtr.get(loopPtr.size() - 1) + 1;
				} else {
					codePtr++;
					loopPtr.remove(loopPtr.size() - 1);
				}
				break;
			}
		}

		return res;
	}

}
