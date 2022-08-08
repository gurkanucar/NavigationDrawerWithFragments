package com.gucarsoft.navigationdrawerwithfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.gucarsoft.navigationdrawerwithfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                it.isChecked = true
                when (it.itemId) {
                    R.id.home -> {
                        replaceFragment(HomeFragment(), it.title.toString())
                        Toast.makeText(this@MainActivity, "Home", Toast.LENGTH_SHORT).show()
                    }
                    R.id.message -> {
                        replaceFragment(MessageFragment(), it.title.toString())
                        Toast.makeText(this@MainActivity, "Message", Toast.LENGTH_SHORT).show()
                    }
                    R.id.settings -> {
                        replaceFragment(SettingsFragment(), it.title.toString())
                        Toast.makeText(this@MainActivity, "Settings", Toast.LENGTH_SHORT).show()
                    }
                    R.id.login -> {
                        replaceFragment(LoginFragment(), it.title.toString())
                        Toast.makeText(this@MainActivity, "Login", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }

    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_1, fragment)
        fragmentTransaction.commit()
        binding.drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}