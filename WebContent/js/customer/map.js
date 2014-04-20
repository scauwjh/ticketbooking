/**
 * @author wjh
 * @date 2014-2-28
 *
 * 利用二叉排序树以及哈希实现javascript的map
 * 因为hash的值并不是唯一的，会有出错的概率，但概率非常非常低，可以忽略
 * 不足之处是并非平衡树，最坏情况会出现n次查找
 * 改善需要用到红黑树（R-B Tree），以后有机会再改进 
 */
function Map() {
	var tree = new Array();
	var MOD = 100000007;
	function node() {
		this.key = null;
		this.val = null;
	} 
	/**
	 * insert into the tree
	 */
	function insert(index, key, value) {
		if (tree[index] == null) {
			tree[index] = new node();
			tree[index].val = value;
			tree[index].key = key
		}
		//The same key will cover
		else if (tree[index].key == key) {
			tree[index].val = value;
		}
		else if (tree[index].key < key) {
			insert(index*2+1, key, value);
		}
		else {
			insert(index*2+2, key, value);
		}
	}
	/**
	 * to search the key
	 */
	function search(index, key) {
		if (tree[index] == null)
			return null;
		if (tree[index].key == key)
			return tree[index];
		if (tree[index].key < key)
			return search(index*2+1, key);
		return search(index*2+2, key);
	}

	/**
	 * pow
	 * mod is MOD
	 */
	function pow(key, square) {
		var res = 1;
		for (var i = 0; i < square; i++) {
			res *= key % MOD;
			res %= MOD;
		}
		return res;
	}

	/**
	 * 52 hexadecimal mod MOD
	 * limit the key as A-Z or a-z
	 */
	function hash(key) {
		var res = 0;
		for (var i = 0; i < key.length; i++) {
			var asc = key.charCodeAt(i);
			asc = asc > 96 ? asc - 97 : asc - 39;
			res += pow(53, key.length - i) * asc % MOD;
			res %= MOD;
		}
		return res;
	}

	/**
	 * map's put
	 */
	var put = function(key, value) {
		insert(0, hash(key), value);
	}

	/**
	 * map's get
	 * if not exist will return null
	 */
	var get = function(key) {
		var obj = search(0, hash(key));
		return (obj != null ? obj.val : null);
	}

	/* The following lines is for debugging */
	/*
	function pre_traversal_print(index) {
		if (tree[index] == null)
			return;
		console.log("hash_key:" + tree[index].key + " value:" + tree[index].val);
		pre_traversal_print(index*2+1);
		pre_traversal_print(index*2+2);
	}

	var debug = function() {
		var key = ["hello", "world", "my", "name", "is", "wjh", "dfdfwhffgfgedfdggvgfggiemdhwsfkhges"];
		var value = [1, 2, 3, 4, 5, 6, 7];
		for (var i = 0; i < key.length; i++) {
			put(key[i], value[i]);
		}
		for (var i = 0; i < key.length; i++)
			console.log(get(key[i]));
		pre_traversal_print(0);
	}
	this.debug = debug;
	*/
	/* end debug */
	
	
	this.put = put;
	this.get = get;
}


// // debug
// var map = new Map();
// map.debug();