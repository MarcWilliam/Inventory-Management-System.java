/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaces;

/**
 *
 * @author marcw
 */
public interface IHashMap extends IFilePath {

	public long getID();

	public boolean Save();

	public boolean Delete();
}
