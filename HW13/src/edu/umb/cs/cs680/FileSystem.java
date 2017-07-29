package edu.umb.cs.cs680;

public class FileSystem {
	
	private static FileSystem instance;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (instance == null){
			instance = new FileSystem();
			
		}
		return instance;
	}
	
	private static void clearSystem(){
		// Set singleton instance to null
		// to clear out old instance
		// in case one exists
		instance = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "root");
				this.root.setOwner("osSystem");
			}
		
		return this.root;
	}
	
	public void showAllElements(){
		
		if (root.getChildren().size() > 0){	
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(root + "\n");
				
				for (FSElement e : root.getChildren()){
					
					sb.append("\t" + e + "\n");
					
					if (!e.isFile() && !e.isLink()){
						
						Directory dir = root.getDirectory(e.getName());
						
						this.tab=2;
						
						for (FSElement a :dir.getChildren()){
							
							sb.append("\t\t" + a + "\n");
							
							moreElements(a, this.tab, sb);
							
						}
						
					}
					
				}
			
			System.out.print(sb);
			
		} else {
			System.out.println("Root directory is empty.");
		}
		
	}
	
	
	
	public void moreElements (FSElement a, int num, StringBuilder s){
		
		StringBuilder sb = s;
	
		if (!a.isFile() && !a.isLink()){
			
			Directory dir1 = (Directory) a;
			
			dir1.getDirectory(a.getName());
			
			this.tab++;
			
			for (FSElement b :dir1.getChildren()){
			
				sb.append(getTab() + b + "\n");
				
				moreElements(b, this.tab, sb);
			}
		}
		
	}
	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}
	
	public static void main (String args[]){
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.txt", 10);
		File b = new File(system, "b.txt", 10);
		File c = new File(system, "c.txt", 10);
		
		
		Directory home = new Directory(fileSystem.root, "home");
		File d = new File(home, "d", 10);
		Link x = new Link(home, "x.jpg");
		x.setElement(system);
		
		Directory pictures = new Directory(home, "pictures");
		File e = new File(pictures, "e.jpg", 10);
		File f = new File(pictures, "f.jpg", 10);
		Link y = new Link(pictures, "y.jpg", e);
		Link x2 = new Link(pictures, "x2.jpg", x);
		Link x3 = new Link(pictures, "x3.jpg", x2);
		
		Directory newPics = new Directory(pictures, "new pics");
		File g = new File(pictures, "g.jpg", 10 );
		File h = new File(pictures, "h.jpg", 10);
		
		Directory moreNewPics = new Directory(pictures, "more new pics");
		File i = new File(pictures, "i.jpg", 10);
		File j = new File(pictures, "j.jpg", 10);
		
		Directory evenMoreNewPics = new Directory(pictures, "even more new pics");
		File k = new File(pictures, "k.jpg", 10);
		File l = new File(pictures, "l.jpg", 10);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);
		pictures.appendChild(x2);
		pictures.appendChild(x3);
		
		newPics.appendChild(g);
		newPics.appendChild(h);
		
		moreNewPics.appendChild(i);
		moreNewPics.appendChild(j);
		
		evenMoreNewPics.appendChild(k);
		evenMoreNewPics.appendChild(l);
		
		home.appendChild(d);
		home.appendChild(pictures);
		home.appendChild(x);
		
		pictures.appendChild(newPics);
		newPics.appendChild(moreNewPics);
		moreNewPics.appendChild(evenMoreNewPics);
		
		fileSystem.showAllElements();
		
		
		System.out.println("\nLink x size: " + x.getTargetSize());
		
		System.out.println("Link y size: " + y.getTargetSize());
		
		System.out.println("System folder size: " + system.getSize());
		
		System.out.println("Link x3 size: " + x3.getTargetSize());
		
		System.out.println("Root folder size: " + fileSystem.getRootDirectory().getSize());

		
		
	}


}
