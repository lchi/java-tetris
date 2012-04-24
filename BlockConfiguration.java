package tetris;

import java.util.ArrayList;

/**
 * Factory for block 'configurations'.
 * These are the different rotations for given pieces,
 * and I figured this would be simpler than rotations.
 * 
 * @author dev
 *
 */
public class BlockConfiguration {
	
	public static ArrayList<Block[][]> getForType(char type) {
		// types from http://en.wikipedia.org/wiki/Tetris
		ArrayList<Block[][]> list;
		switch (type) { 
		case 'i':
			list = new ArrayList<Block[][]>(2);
			list.add(new Block[][] { 
					{ new Block() },
					{ new Block() },
					{ new Block() },
					{ new Block() } 
			});
			list.add(new Block[][] { 
					{ new Block(), new Block(), new Block(), new Block() } 
			});
			return list;
		case 's':
			list = new ArrayList<Block[][]>(2);
			list.add(new Block[][] { 
					{ null, new Block(), new Block() },
					{ new Block(), new Block(), null } 
			});
			list.add(new Block[][] { 
					{ new Block(), null },
					{ new Block(), new Block() },
					{ null, new Block() } 
			});
			return list;
		case 'z':
			list = new ArrayList<Block[][]>(2);
			list.add(new Block[][] { 
					{ new Block(), new Block(), null },
					{ null, new Block(), new Block() } 
			});
			list.add(new Block[][] { 
					{ null, new Block() },
					{ new Block(), new Block() },
					{ new Block(), null } 
			});
			return list;
		case 'o':
			list = new ArrayList<Block[][]>(1);
			list.add(new Block[][] { 
					{ new Block(), new Block() },
					{ new Block(), new Block() } 
			});
			return list;
		case 'l':
			list = new ArrayList<Block[][]>(4);
			list.add(new Block[][] { 
					{ new Block(), null },
					{ new Block(), null },
					{ new Block(), new Block() }
			});
			list.add(new Block[][] { 
					{ null, null, new Block() },
					{ new Block(), new Block(), new Block() }
			});
			list.add(new Block[][] { 
					{ new Block(), new Block() },
					{ null, new Block() },
					{ null, new Block() }
			});
			list.add(new Block[][] { 
					{ new Block(), new Block(), new Block() },
					{ new Block(), null, null }
			});
			return list;
		case 'j':
			list = new ArrayList<Block[][]>(4);
			list.add(new Block[][] { 
					{ null, new Block() },
					{ null, new Block() },
					{ new Block(), new Block() }
			});
			list.add(new Block[][] { 
					{ new Block(), new Block(), new Block() },
					{ null, null, new Block() }
			});
			list.add(new Block[][] { 
					{ new Block(), new Block() },
					{ new Block(), null },
					{ new Block(), null }
			});
			list.add(new Block[][] { 
					{ new Block(), null, null },
					{ new Block(), new Block(), new Block() }
			});
			return list;
		case 't':
			list = new ArrayList<Block[][]>(4);
			list.add(new Block[][] { 
					{ null, new Block(), null },
					{ new Block(), new Block(), new Block() }
			});
			list.add(new Block[][] { 
					{ new Block(), null },
					{ new Block(), new Block() },
					{ new Block(), null }
			});
			list.add(new Block[][] { 
					{ new Block(), new Block(), new Block() },
					{ null, new Block(), null }
			});
			list.add(new Block[][] { 
					{ null, new Block() },
					{ new Block(), new Block() },
					{ null, new Block() }
			});
			return list;
		default: return null;
		}
	}
	
}
