# Hashing

When we have a key $k$ of some type, we want to define a hash function $h(k): K \rightarrow \mathbb{N}$.

Ideally, this hash function should distribute $k$ deterministically but uniformly distributed across $\mathbb{N}$ (or some range in $\mathbb{N}$).


# Open addressing

Suppose that we have $k_1$ and $k_2$ with $k_1 \neq k_2$ that has:

$$
h(k_1) = h(k_2)
$$

Essentially, we have a collision.

Open addressing specifies the procedure of determining the next hash value when we get a collision.

Suppose that we insert at index determined by $g(k, i)$. Where $i$ is the probe number. Essentially, imagine we have: $k_0, k_1, \dots , k_i , \dots , k_n$ which all have the same hash.

We essentially say:

$$
g(k_0, i) = (h(k_0) + f(k_0, i)) \mod m
$$

Where $m$ is the capacity, and $f$ is the probing function.

When we do linear probing: $f(k_0, i) = i$.

When we do quadratic probing: $f(k_0, i) = i^2$

When we do double hashing: $f(k_0, i) = i \cdot h_2(k)$ with $h_2(k) = c - (h_1(k) \mod m)$ for some prime $c$

## Faults in Open Addressing


